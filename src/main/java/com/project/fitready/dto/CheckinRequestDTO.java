package com.project.fitready.dto;

import java.util.List;

public record CheckinRequestDTO(
        Long idTreino,
        Long tempoTreino,
        String dataTreino,
        Long calorias,
        Double peso,
        Long idIntensidade,
        List<ExercicioDTO> exercicios) {

}
