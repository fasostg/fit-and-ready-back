package com.project.fitready.entity;

import com.project.fitready.dto.CheckinRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "checkin")
@Entity(name = "Checkin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checkin {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_treino")
    private Treino treino;
    private Long tempoTreino;
    private Long calorias;
    private Double peso;
    private LocalDateTime data;

}
