package com.project.fitready.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum IntensidadeEnum {

    BAIXA(1L, "Baixa"),
    MEDIA(2L, "Média"),
    ALTA(3L, "Alta");

    private final Long id;
    private final String nome;
}
