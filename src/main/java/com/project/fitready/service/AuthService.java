package com.project.fitready.service;

import com.project.fitready.dto.LoginRequestDTO;
import com.project.fitready.dto.RegisterRequestDTO;
import com.project.fitready.entity.Usuario;
import com.project.fitready.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JWTService jwtService;

    public String login(LoginRequestDTO dto) {
        var cpf = removerMascaraCpf(dto.cpf());

        var usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var encoder = jwtService.passwordEncoder();
        if (!encoder.matches(dto.senha(), usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        return jwtService.gerarToken(usuario.getCpf());
    }

    public void cadastrar(RegisterRequestDTO dto) {
        if(usuarioRepository.findByCpf(dto.cpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado");
        }

        var usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setCpf(removerMascaraCpf(dto.cpf()));

        var encoder = jwtService.passwordEncoder();
        usuario.setSenha(encoder.encode(dto.senha()));

        usuarioRepository.save(usuario);
    }

    private String removerMascaraCpf(String cpf) {
        return  cpf.replaceAll("[^0-9]", "");
    }

}
