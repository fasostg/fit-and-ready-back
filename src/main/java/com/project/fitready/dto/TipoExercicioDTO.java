package com.project.fitready.dto;

import com.project.fitready.domain.TipoExercicio;

public record TipoExercicioDTO(Long id, String nome, GrupoMuscularDTO grupoMuscular) {

    public TipoExercicioDTO(TipoExercicio tipoExercicio) {
        this(tipoExercicio.getId(),
                tipoExercicio.getNome(),
                new GrupoMuscularDTO(tipoExercicio.getGrupoMuscular()));
    }
}
