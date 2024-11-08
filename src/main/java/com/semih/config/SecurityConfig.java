package com.semih.config;

import com.semih.handler.AuthEntryPoint;
import com.semih.jwt.JWTAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String REGISTER = "/register";
    private static final String AUTHENTICATE = "/authenticate";
    private static final String REFRESH_TOKEN = "/refreshToken";
    private static final String[] SWAGGER_PATHS={
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
    };

    private final AuthenticationProvider authenticationProvider;

    private final JWTAuthenticationFilter authenticationFilter;

    private final AuthEntryPoint authEntryPoint;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JWTAuthenticationFilter authenticationFilter, AuthEntryPoint authEntryPoint) {
        this.authenticationProvider = authenticationProvider;
        this.authenticationFilter = authenticationFilter;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(request -> request
                        .requestMatchers("/register", "/authenticate", "/refreshToken").permitAll()
                        .requestMatchers(SWAGGER_PATHS).permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authEntryPoint)
                        .accessDeniedHandler((request, response, ex) -> response.sendError(HttpStatus.FORBIDDEN.value())))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
