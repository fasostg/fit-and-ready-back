package com.project.fitready.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "d_grupo_muscular")
@Entity(name = "GrupoMuscular")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoMuscular {

    @Id
    private Long id;
    private String nome;
}
