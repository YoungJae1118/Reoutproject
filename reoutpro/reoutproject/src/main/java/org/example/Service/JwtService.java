package org.example.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private String secret = "long-long-long-long-long-long-long";


    /**
     * 토큰 만들기
     */
    public String createJwt(Long memberId) {

        // 1. 서명 만들기
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        // 2. 데이터 준비
        String subject = memberId.toString(); // 사용자 준비
        Date now = new Date();                // 현재시간
        Date expiration = new Date(now.getTime() + 1000 * 60); // 만료시간 설정 1분뒤

        // 2. 토큰 만들기
        String jwt = Jwts.builder()
                .subject(subject)
                .issuedAt(now) //토큰 생성 시간
                .claim("role", "admin") // 💡 커스텀 하게 활용하는 방법
                .claim("key1", "value1")
                .claim("key2", "value2")
                .claim("key3", "value3")
                .expiration(expiration) //토큰 만료 시간
                .signWith(secretKey) //비밀키로 서명
                .compact(); //문자열로 바꾸기
        return jwt;
    }
}
