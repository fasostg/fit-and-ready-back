package com.project.fitready.service;

import com.project.fitready.dto.LoginRequestDTO;
import com.project.fitready.dto.RegisterRequestDTO;
import com.project.fitready.entity.Usuario;
import com.project.fitready.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JWTService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_success() {
        LoginRequestDTO dto = mock(LoginRequestDTO.class);
        when(dto.cpf()).thenReturn("123.456.789-00");
        when(dto.senha()).thenReturn("senha");

        Usuario usuario = new Usuario();
        usuario.setCpf("12345678900");
        usuario.setSenha("hashedSenha");

        when(usuarioRepository.findByCpf("12345678900")).thenReturn(Optional.of(usuario));
        when(jwtService.passwordEncoder()).thenReturn(passwordEncoder);
        when(passwordEncoder.matches("senha", "hashedSenha")).thenReturn(true);
        when(jwtService.gerarToken("12345678900")).thenReturn("token");

        String token = authService.login(dto);
        assertEquals("token", token);
    }

    @Test
    void login_userNotFound() {
        LoginRequestDTO dto = mock(LoginRequestDTO.class);
        when(dto.cpf()).thenReturn("123.456.789-00");
        when(usuarioRepository.findByCpf("12345678900")).thenReturn(Optional.empty());

        when(jwtService.passwordEncoder()).thenReturn(passwordEncoder);

        assertThrows(RuntimeException.class, () -> authService.login(dto));
    }

    @Test
    void login_incorrectPassword() {
        LoginRequestDTO dto = mock(LoginRequestDTO.class);
        when(dto.cpf()).thenReturn("123.456.789-00");
        when(dto.senha()).thenReturn("senha");

        Usuario usuario = new Usuario();
        usuario.setCpf("12345678900");
        usuario.setSenha("hashedSenha");

        when(usuarioRepository.findByCpf("12345678900")).thenReturn(Optional.of(usuario));
        when(jwtService.passwordEncoder()).thenReturn(passwordEncoder);
        when(passwordEncoder.matches("senha", "hashedSenha")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authService.login(dto));
    }

    @Test
    void cadastrar_success() {
        RegisterRequestDTO dto = mock(RegisterRequestDTO.class);
        when(dto.cpf()).thenReturn("123.456.789-00");
        when(dto.nome()).thenReturn("Nome");
        when(dto.senha()).thenReturn("senha");

        when(usuarioRepository.findByCpf("12345678900")).thenReturn(Optional.empty());
        when(jwtService.passwordEncoder()).thenReturn(passwordEncoder);
        when(passwordEncoder.encode("senha")).thenReturn("hashedSenha");

        authService.cadastrar(dto);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void cadastrar_cpfAlreadyRegistered() {
        RegisterRequestDTO dto = mock(RegisterRequestDTO.class);
        when(dto.cpf()).thenReturn("123.456.789-00");
        when(usuarioRepository.findByCpf("12345678900")).thenReturn(Optional.of(new Usuario()));

        assertThrows(RuntimeException.class, () -> authService.cadastrar(dto));
    }
}

