package org.example.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private String secret = "long-long-long-long-long-long-long";

    public void createJwt(Long userId) {
        // 1. 서명 만들기
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        //2. 데이터 준비하기
        String subject = userId.toString();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 3000 * 60); //3분

        String jwt = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .claim("key","value")
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();
        System.out.println(jwt);
    }


}
