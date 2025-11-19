package com.project.fitready.dto;

import com.project.fitready.domain.TipoExercicio;

public record TipoExercicioDTO(Long id, String nome, String grupoMuscular) {

    public TipoExercicioDTO(TipoExercicio tipoExercicio) {
        this(tipoExercicio.getId(),
                tipoExercicio.getNome(),
                tipoExercicio.getGrupoMuscular().getNome());
    }
}
