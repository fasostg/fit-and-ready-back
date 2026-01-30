package com.project.fitready.dto;

import com.project.fitready.domain.UnidadeMedida;

public record UnidadeMedidaDTO(Long id, String nome) {

    public UnidadeMedidaDTO(UnidadeMedida unidadeMedida) {
        this(unidadeMedida.getId(), unidadeMedida.getNome());
    }
}
