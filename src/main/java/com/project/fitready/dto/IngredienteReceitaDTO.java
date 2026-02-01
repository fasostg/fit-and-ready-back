package com.project.fitready.dto;

import com.project.fitready.entity.IngredienteReceita;

public record IngredienteReceitaDTO(Long id, IngredienteDTO ingrediente, Double quantidade, UnidadeMedidaDTO unidadeMedida) {

    public IngredienteReceitaDTO(IngredienteReceita ingredienteReceita) {
        this(ingredienteReceita.getId(),
                new IngredienteDTO(ingredienteReceita.getIngrediente()),
                ingredienteReceita.getQuantidade(),
                new UnidadeMedidaDTO(ingredienteReceita.getUnidadeMedida()));
    }
}
