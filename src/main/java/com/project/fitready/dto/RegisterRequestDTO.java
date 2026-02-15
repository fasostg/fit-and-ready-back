package com.project.fitready.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotBlank String senha) {}
