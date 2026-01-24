package com.project.fitready.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

@AllArgsConstructor
public enum PeriodoEnum {

    SEMANA("semana"),
    MES("mes"),
    ANO("ano");

    private final String name;

    public static PeriodoEnum getByName(String name) {
        return Arrays.stream(PeriodoEnum.values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElse(null);
    }
}
