package com.semih.jwt;

import com.semih.exception.BaseException;
import com.semih.exception.ErrorMessage;
import com.semih.exception.MessageType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    private final UserDetailsService userDetailsService;

    public JWTAuthenticationFilter(JWTService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token;
        String userName;

        token  = header.substring(7);
        try{
            userName = jwtService.getUsernameByToken(token);
            // eger oturum acmamıs ıse ve null degılse
            if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                if(userDetails!=null && jwtService.isTokenValid(token)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(userDetails);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (ExpiredJwtException ex){
            throw new BaseException(new ErrorMessage(ex.getMessage(),MessageType.TOKEN_IS_EXPIRED));
        } catch (Exception e){
            throw new BaseException(new ErrorMessage(e.getMessage(),MessageType.GENERAL_EXCEPTION));
        }

        filterChain.doFilter(request, response);

    }
}
