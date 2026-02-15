package com.project.fitready.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JWTService {

    private final String KEY = "24faba635f59db2e2bf71c624b321188";
    private final long EXPIRATION_TIME = 10800000; // 3 horas

    public String gerarToken(String cpf) {
        return Jwts.builder()
                .setSubject(cpf)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
