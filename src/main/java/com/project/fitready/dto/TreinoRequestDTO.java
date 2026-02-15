package com.project.fitready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TreinoRequestDTO(
        Long id,
        @NotBlank String nome,
        @NotNull TipoTreinoDTO tipoTreino,
        @NotNull List<ExercicioDTO> exercicios,
        @NotBlank String dataInicio) {
}
