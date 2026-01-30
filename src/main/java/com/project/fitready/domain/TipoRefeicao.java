package com.project.fitready.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "d_tipo_refeicao")
@Entity(name = "TipoRefeicao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoRefeicao {

    @Id
    private Long id;
    private String nome;
}
