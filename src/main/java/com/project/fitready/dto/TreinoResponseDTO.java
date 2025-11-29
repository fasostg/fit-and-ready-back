package com.project.fitready.dto;

import com.project.fitready.entity.Checkin;
import com.project.fitready.entity.Treino;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record TreinoResponseDTO(Long id, String nome, TipoTreinoDTO tipoTreino, List<ExercicioDTO> exercicios, LocalDate dataInicio, LocalDate dataFim) {

    public TreinoResponseDTO(Treino treino) {
        this(treino.getId(),
                treino.getNome(),
                new TipoTreinoDTO(treino.getTipoTreino()),
                treino.getExercicios().stream().map(ExercicioDTO::new).toList(),
                treino.getDataInicio(),
                treino.getDataFim());
    }
}
