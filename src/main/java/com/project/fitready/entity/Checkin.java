package com.project.fitready.entity;

import com.project.fitready.domain.Intensidade;
import com.project.fitready.json.DadosExerciciosJson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

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
    @ManyToOne
    @JoinColumn(name = "id_intensidade")
    private Intensidade intensidade;
    private LocalDate data;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "js_dados_exercicios", columnDefinition = "jsonb")
    private DadosExerciciosJson dadosExercicios;
    @Column(name = "cpf_usuario")
    private String cpfUsuario;

}
