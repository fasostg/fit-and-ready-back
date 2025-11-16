package com.project.fitready.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "d_tipo_exercicio")
@Entity(name = "TipoExercicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoExercicio {

    @Id
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_grupo_muscular")
    private GrupoMuscular grupoMuscular;
}
