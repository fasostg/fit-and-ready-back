package com.project.fitready.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class JWTServiceTest {

    private final JWTService jwtService = new JWTService();

    @Test
    void gerarToken_shouldGenerateValidToken() {
        String cpf = "12345678900";
        String token = jwtService.gerarToken(cpf);
        assertNotNull(token);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey("24faba635f59db2e2bf71c624b321188".getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals(cpf, claims.getSubject());
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
    }

    @Test
    void passwordEncoder_shouldEncodeAndMatchPassword() {
        PasswordEncoder encoder = jwtService.passwordEncoder();
        String rawPassword = "senha123";
        String encoded = encoder.encode(rawPassword);
        assertTrue(encoder.matches(rawPassword, encoded));
    }
}

