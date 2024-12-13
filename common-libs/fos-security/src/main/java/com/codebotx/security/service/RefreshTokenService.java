package com.codebotx.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codebotx.security.data.RefreshToken;
import com.codebotx.security.exception.exception.SignInException;
import com.codebotx.security.exception.exception.TokenRefreshException;
import com.codebotx.security.repository.RefreshTokenRepository;
import com.codebotx.security.repository.AuthUserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${spring.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AuthUserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(UUID userId) {
        System.out.println("I am the user id in  createRefreshToken   \n "+userId);
        Optional<RefreshToken> isAlreadyExist = refreshTokenRepository.findByUserId(userId);

        isAlreadyExist.ifPresent(refreshToken -> refreshTokenRepository.delete(refreshToken));
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpirationDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpirationDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired!. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(UUID userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }

}
