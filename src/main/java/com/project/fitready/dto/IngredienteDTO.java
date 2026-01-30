package com.project.fitready.dto;

import com.project.fitready.domain.Ingrediente;

public record IngredienteDTO(Long id, String nome) {

    public IngredienteDTO(Ingrediente ingrediente) {
        this(ingrediente.getId(), ingrediente.getNome());
    }
}
