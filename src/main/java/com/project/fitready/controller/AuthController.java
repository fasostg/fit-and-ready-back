package com.project.fitready.controller;

import com.project.fitready.dto.LoginRequestDTO;
import com.project.fitready.dto.RegisterRequestDTO;
import com.project.fitready.repository.UsuarioRepository;
import com.project.fitready.service.AuthService;
import com.project.fitready.service.JWTService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO dto) {
        var jwtToken = authService.login(dto);
        return ResponseEntity.ok().body(jwtToken);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid RegisterRequestDTO dto) {
        authService.cadastrar(dto);
        return ResponseEntity.ok().build();
    }
}
