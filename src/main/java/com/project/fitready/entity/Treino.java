package com.project.fitready.entity;

import com.project.fitready.domain.TipoTreino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "treino")
@Entity(name = "Treino")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treino {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_tipo_treino")
    private TipoTreino tipoTreino;
    //private List<Exercicio> exercicios;
    private Long calorias;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}
