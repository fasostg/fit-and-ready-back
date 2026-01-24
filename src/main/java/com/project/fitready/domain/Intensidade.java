package com.project.fitready.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "d_intensidade")
@Entity(name = "Intensidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Intensidade {

    @Id
    private Long id;
    private String nome;
}
