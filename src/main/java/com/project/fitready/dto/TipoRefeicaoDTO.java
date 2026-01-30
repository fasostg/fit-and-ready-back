package com.project.fitready.dto;

import com.project.fitready.domain.TipoRefeicao;

public record TipoRefeicaoDTO(Long id, String nome) {

    public TipoRefeicaoDTO(TipoRefeicao tipoRefeicao) {
        this(tipoRefeicao.getId(), tipoRefeicao.getNome());
    }
}
