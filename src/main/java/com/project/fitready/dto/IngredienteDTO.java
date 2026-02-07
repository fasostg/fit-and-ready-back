package com.project.fitready.dto;

import com.project.fitready.domain.Ingrediente;

public record IngredienteDTO(Long id, String nome, Double calorias, Double proteinas, Double carboidratos, Double gorduras) {

    public IngredienteDTO(Ingrediente ingrediente) {
        this(ingrediente.getId(),
                ingrediente.getNome(),
                ingrediente.getCalorias(),
                ingrediente.getProteinas(),
                ingrediente.getCarboidratos(),
                ingrediente.getGorduras());
    }
}
