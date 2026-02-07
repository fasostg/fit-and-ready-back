package com.project.fitready.dto;

import com.project.fitready.entity.IngredienteReceita;

public record IngredienteReceitaDTO(Long id, IngredienteDTO ingrediente, Double quantidade) {

    public IngredienteReceitaDTO(IngredienteReceita ingredienteReceita) {
        this(ingredienteReceita.getId(),
                new IngredienteDTO(ingredienteReceita.getIngrediente()),
                ingredienteReceita.getQuantidade());
    }
}
