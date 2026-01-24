package com.project.fitready.dto;

import com.project.fitready.entity.Exercicio;

public record ExercicioDTO(Long id, TipoExercicioDTO tipoExercicio, Long numeroSeries, Long numeroRepeticoes, Long carga, String observacao) {

    public ExercicioDTO(Exercicio exercicio) {
        this(exercicio.getId(),
                new TipoExercicioDTO(exercicio.getTipoExercicio()),
                exercicio.getNumeroSeries(),
                exercicio.getNumeroRepeticoes(),
                exercicio.getCarga(),
                exercicio.getObservacao());
    }
}
