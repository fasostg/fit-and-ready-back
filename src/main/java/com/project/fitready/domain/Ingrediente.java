package com.project.fitready.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "d_ingrediente")
@Entity(name = "Ingrediente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {

    @Id
    private Long id;
    private String nome;
    private Double calorias;
    private Double proteinas;
    private Double carboidratos;
    private Double gorduras;
}
