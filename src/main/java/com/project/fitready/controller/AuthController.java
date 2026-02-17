package com.project.fitready.controller;

import com.project.fitready.dto.LoginRequestDTO;
import com.project.fitready.dto.RegisterRequestDTO;
import com.project.fitready.repository.UsuarioRepository;
import com.project.fitready.service.AuthService;
import com.project.fitready.service.JWTService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO dto) {
        String jwtToken;
        try {
            jwtToken = authService.login(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        return ResponseEntity.ok().body(jwtToken);
    }

    @PostMapping("/cadastrar")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid RegisterRequestDTO dto) {
        try {
            authService.cadastrar(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
