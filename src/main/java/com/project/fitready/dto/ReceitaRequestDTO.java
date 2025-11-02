package com.project.fitready.dto;

import java.util.List;

public record ReceitaRequestDTO(
        String nome,
        String ingredientes,
        String modoPreparo,
        Long tempoPreparo) {
}
