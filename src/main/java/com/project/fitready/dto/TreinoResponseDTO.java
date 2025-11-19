package com.project.fitready.dto;

import com.project.fitready.entity.Checkin;
import com.project.fitready.entity.Treino;

import java.time.LocalDateTime;
import java.util.List;

public record TreinoResponseDTO(Long id, String nome, String tipoTreino, List<ExercicioDTO> exercicios, LocalDateTime dataInicio, LocalDateTime dataFim) {

    public TreinoResponseDTO(Treino treino) {
        this(treino.getId(),
                treino.getNome(),
                treino.getTipoTreino().getNome(),
                treino.getExercicios().stream().map(ExercicioDTO::new).toList(),
                treino.getDataInicio(),
                treino.getDataFim());
    }
}
