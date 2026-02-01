package com.project.fitready.entity;

import com.project.fitready.dto.ReceitaRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "receita")
@Entity(name = "Receita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredienteReceita> ingredientesReceita;
    private String modoPreparo;
    private Long tempoPreparo;
    private Long calorias;
    private Long proteinas;
    private Long carboidratos;
    private Long gorduras;
}
