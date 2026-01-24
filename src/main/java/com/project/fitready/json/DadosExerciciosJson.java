package com.project.fitready.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosExerciciosJson implements Serializable {

    private List<ExercicioJson> exercicios;
}
