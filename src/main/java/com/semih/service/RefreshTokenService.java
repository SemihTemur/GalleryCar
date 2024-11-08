package com.semih.service;

import com.semih.entity.RefreshToken;
import com.semih.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken saveRefreshToken(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> getRefreshToken(String refreshToken) {
        return  refreshTokenRepository.findByRefreshToken(refreshToken);
    }

}

