package com.project.fitready.dto;

import java.util.List;

public record ReceitaRequestDTO(
        String nome,
        List<IngredienteReceitaDTO> ingredientesReceita,
        String modoPreparo,
        Long tempoPreparo) {
}
