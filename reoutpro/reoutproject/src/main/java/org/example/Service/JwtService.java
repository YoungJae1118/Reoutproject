package org.example.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.security.auth.Subject;
import java.util.Date;

@Service
public class JwtService {
    private String secret = "long-long-long-long-long-long-long";

    //JWT 만들기
    public String createJwt (Long userId) {
        // 1. Create Signature
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        // 2. Create Data
        String subject = userId.toString();
        Date issued = new Date();  // now
        Date expiration = new Date(issued.getTime() + 3000 * 60);

        // 3. Create JWT
        String jwt = Jwts.builder()
                .subject(subject)
                .issuedAt(issued)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
                return jwt;
    }
}
