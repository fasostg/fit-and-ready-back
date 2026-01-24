package com.project.fitready.json;

import com.project.fitready.domain.TipoExercicio;
import com.project.fitready.entity.Exercicio;
import com.project.fitready.entity.Treino;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioJson implements Serializable {

    private Long idTipoExercicio;
    private String nomeTipoExercicio;
    private Long idGrupoMuscular;
    private Long numeroSeries;
    private Long numeroRepeticoes;
    private Long carga;
    private String observacao;

    public ExercicioJson(Exercicio exercicio) {
        if (exercicio == null) return;

        this.idTipoExercicio = exercicio.getTipoExercicio().getId();
        this.nomeTipoExercicio = exercicio.getTipoExercicio().getNome();
        this.idGrupoMuscular = exercicio.getTipoExercicio().getGrupoMuscular().getId();
        this.numeroSeries = exercicio.getNumeroSeries();
        this.numeroRepeticoes = exercicio.getNumeroRepeticoes();
        this.carga = exercicio.getCarga();
        this.observacao = exercicio.getObservacao();
    }
}
