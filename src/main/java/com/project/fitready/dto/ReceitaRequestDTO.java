package com.project.fitready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReceitaRequestDTO(
        Long id,
        @NotBlank String nome,
        @NotNull List<IngredienteReceitaDTO> ingredientesReceita,
        @NotBlank String modoPreparo,
        @NotNull Long tempoPreparo,
        @NotNull TipoRefeicaoDTO tipoRefeicao) {
}
