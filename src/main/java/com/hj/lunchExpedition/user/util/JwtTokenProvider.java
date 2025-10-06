package com.hj.lunchExpedition.user.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private Key key;
    private final long TOKEN_VALIDITY = 1000L * 60 * 60 * 24 * 7; // 7일

    @PostConstruct
    public void init() {
        // 간단한 비밀키 (실서비스에서는 환경변수로 관리!)
        String secret = "hjLunchExpeditionSuperSecretKeyForJWTGeneration1234";
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /** 토큰 생성 */
    public String generateToken(Long userId, String nickname) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + TOKEN_VALIDITY);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("nickname", nickname)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /** 토큰 유효성 검사 */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /** 토큰에서 userId 추출 */
    public Long getUserId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
