package com.project.fitready.entity;

import com.project.fitready.domain.GrupoMuscular;
import com.project.fitready.domain.TipoExercicio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "exercicio")
@Entity(name = "Exercicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercicio {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_exercicio")
    private TipoExercicio tipoExercicio;
    private Long numeroSeries;
    private Long numeroRepeticoes;
    private LocalDateTime dataFim;
}
