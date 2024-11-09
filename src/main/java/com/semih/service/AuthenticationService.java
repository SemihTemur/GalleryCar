package com.semih.service;

import com.semih.dto.request.AuthRequest;
import com.semih.dto.request.RefreshTokenRequest;
import com.semih.dto.response.AuthResponse;
import com.semih.dto.response.UserResponse;
import com.semih.entity.RefreshToken;
import com.semih.entity.User;
import com.semih.exception.BaseException;
import com.semih.exception.ErrorMessage;
import com.semih.exception.MessageType;
import com.semih.jwt.JWTService;
import com.semih.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationProvider authenticationProvider;

    private final JWTService jwtService;

    private final RefreshTokenService refreshTokenService;

    public AuthenticationService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationProvider authenticationProvider, JWTService jwtService, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    private User createUser(AuthRequest authRequest) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(authRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }

    public UserResponse register(AuthRequest authRequest) {
        User userSaved = createUser(authRequest);
        userRepository.save(userSaved);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(userSaved, userResponse);
        return userResponse;
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        try{
            // eğer boyle bır kayıt varsa true yoksa hata fırlatır.
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> user = userRepository.findByUsername(authRequest.getUsername());
            if(user.isEmpty()) {
                throw new BaseException(new ErrorMessage("null",MessageType.NO_RECORD_EXIST));
            }
            String accessToken = jwtService.genarateToken(user.get());
            RefreshToken saveRefreshToken = refreshTokenService.saveRefreshToken(createRefreshToken(user.get()));

            return new AuthResponse(accessToken,saveRefreshToken.getRefreshToken());
        } catch(Exception e){
            throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.USERNAME_OR_PASSWORD_INVALID));
        }

    }

    public  boolean isValidRefreshToken(RefreshToken refreshToken) {
        return new Date().before(refreshToken.getExpiredDate());
    }

    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenService.getRefreshToken(refreshTokenRequest.getRefreshToken());
        if(optionalRefreshToken.isEmpty()){
            throw new BaseException(new ErrorMessage(optionalRefreshToken.get().getRefreshToken(),MessageType.USERNAME_NOT_FOUND));
        }
        else if(!isValidRefreshToken(optionalRefreshToken.get())){
            throw new BaseException(new ErrorMessage(optionalRefreshToken.get().getRefreshToken(),MessageType.REFRESH_TOKEN_EXPIRED));
        }

        User user = optionalRefreshToken.get().getUser();
        String accessToken = jwtService.genarateToken(user);
        RefreshToken savedRefreshToken = refreshTokenService.saveRefreshToken(createRefreshToken(user));
        return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
    }

}
