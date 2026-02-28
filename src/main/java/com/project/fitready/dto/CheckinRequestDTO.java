package com.project.fitready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CheckinRequestDTO(
        @NotNull Long idTreino,
        @NotNull Long tempoTreino,
        String dataTreino,
        Long calorias,
        @NotNull Double peso,
        @NotNull Long idIntensidade,
        @NotNull List<ExercicioDTO> exercicios) {

}
