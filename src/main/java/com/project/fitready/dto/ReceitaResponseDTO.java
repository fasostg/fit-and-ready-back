package com.project.fitready.dto;

import com.project.fitready.entity.IngredienteReceita;
import com.project.fitready.entity.Receita;

import java.util.List;

public record ReceitaResponseDTO(
        Long id,
        String nome,
        List<IngredienteReceitaDTO> ingredientes,
        String modoPreparo,
        Long tempoPreparo,
        Long calorias,
        Long proteinas,
        Long carboidratos,
        Long gorduras) {

    public ReceitaResponseDTO(Receita receita) {
        this(receita.getId(),
            receita.getNome(),
            receita.getIngredientesReceita().stream().map(IngredienteReceitaDTO::new).toList(),
            receita.getModoPreparo(),
            receita.getTempoPreparo(),
            receita.getCalorias(),
            receita.getProteinas(),
            receita.getCarboidratos(),
            receita.getGorduras());

    }
}
