package com.project.fitready.dto;

import com.project.fitready.domain.Intensidade;

public record IntensidadeDTO(Long id, String nome) {

    public IntensidadeDTO(Intensidade intensidade) {
        this(intensidade.getId(), intensidade.getNome());
    }
}
