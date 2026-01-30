package com.project.fitready.entity;

import com.project.fitready.domain.Ingrediente;
import com.project.fitready.domain.UnidadeMedida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ingrediente_receita")
@Entity(name = "IngredienteReceita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Ingrediente ingrediente;
    private Double quantidade;

    @ManyToOne
    @JoinColumn(name = "id_unidade_medida")
    private UnidadeMedida unidadeMedida;

    @ManyToOne
    @JoinColumn(name = "id_receita")
    private Receita receita;
}
