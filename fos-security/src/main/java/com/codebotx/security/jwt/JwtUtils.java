package com.codebotx.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.codebotx.security.exception.exception.TokenValidationException;
import com.codebotx.security.service.UserDetailsImpl;

import static org.springframework.web.context.WebApplicationContext.CONTEXT_ATTRIBUTES_BEAN_NAME;
import static org.springframework.web.context.WebApplicationContext.CONTEXT_PARAMETERS_BEAN_NAME;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return generateTokenFromUsername(userPrincipal);
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateTokenFromUsername(UserDetailsImpl userPrincipal) {
        
        Map<String, Object> claims = new HashMap();
        claims.put("userDetails", userPrincipal);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)                  // algo used is HS256
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {

        String a = Jwts.parserBuilder().setSigningKey(key()).build()
        .parseClaimsJws(token).getBody().getSubject();

        System.out.println("\n\nI am in getUserNameFromJwtToken      \n\n "+a);

        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) throws TokenValidationException {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException |
                 SignatureException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
            throw new TokenValidationException(e.getMessage());
        }
    }

}
