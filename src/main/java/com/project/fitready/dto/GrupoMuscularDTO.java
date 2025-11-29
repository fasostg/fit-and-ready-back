package com.project.fitready.dto;

import com.project.fitready.domain.GrupoMuscular;

public record GrupoMuscularDTO(Long id, String nome) {

    public GrupoMuscularDTO(GrupoMuscular grupoMuscular) {
        this(grupoMuscular.getId(), grupoMuscular.getNome());
    }

}
