package com.project.fitready.dto;

import com.project.fitready.entity.Treino;

import java.time.LocalDateTime;
import java.util.List;

public record TreinoRequestDTO(String nome, Long idTipoTreino, List<ExercicioDTO> exercicios, LocalDateTime dataInicio) {
}
