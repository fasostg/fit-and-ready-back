package com.project.fitready.dto;

import com.project.fitready.domain.TipoTreino;

public record TipoTreinoDTO(Long id, String nome) {

    public TipoTreinoDTO(TipoTreino tipoTreino) {
        this(tipoTreino.getId(), tipoTreino.getNome());
    }
}
