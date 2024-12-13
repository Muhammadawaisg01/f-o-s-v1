package com.codebotx.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.codebotx.security.data.RefreshToken;
import com.codebotx.security.data.AuthUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(UUID userId);


//    @Modifying
    int deleteByUser(AuthUser user);
}
