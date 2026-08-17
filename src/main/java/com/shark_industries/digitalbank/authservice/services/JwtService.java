package com.shark_industries.digitalbank.authservice.services;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final String secretKey = "my-super-secret-key-for-digital-bank-jwt-123456789";

    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .setIssuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(key)
                .compact();
    }
}
