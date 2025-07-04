package org.example.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.ParserBuilder;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.security.auth.Subject;
import java.util.Date;

@Service
public class JwtService {
    private String secret = "long-long-long-long-long-long-long";
    // 1. Create Signature
    SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    //JWT 만들기
    public String createJwt (Long userId) {
        // 2. Create Data
        String subject = userId.toString();
        Date issued = new Date();  // now
        Date expiration = new Date(issued.getTime() + 10000 * 60);

        // 3. Create JWT
        String jwt = Jwts.builder()
                .subject(subject)
                .issuedAt(issued)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
                return jwt;
    }

    //JWT 분석하기 12.6 구글링해서 겨우 찾음
    public Long parserJwtForId (String jwt) {
        Claims claims = Jwts.parser() // parser() 사용
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

        return Long.parseLong(claims.getSubject());
    }
}
