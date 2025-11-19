package com.project.fitready.dto;

import com.project.fitready.entity.Exercicio;

public record ExercicioDTO(Long id, TipoExercicioDTO tipoExercicio, Long numeroSeries, Long numeroRepeticoes) {

    public ExercicioDTO(Exercicio exercicio) {
        this(exercicio.getId(),
                new TipoExercicioDTO(exercicio.getTipoExercicio()),
                exercicio.getNumeroSeries(),
                exercicio.getNumeroRepeticoes());
    }
}
