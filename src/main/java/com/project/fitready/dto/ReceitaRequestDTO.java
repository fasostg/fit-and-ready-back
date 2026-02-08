package com.project.fitready.dto;

import java.util.List;

public record ReceitaRequestDTO(
        Long id,
        String nome,
        List<IngredienteReceitaDTO> ingredientesReceita,
        String modoPreparo,
        Long tempoPreparo,
        TipoRefeicaoDTO tipoRefeicao) {
}
