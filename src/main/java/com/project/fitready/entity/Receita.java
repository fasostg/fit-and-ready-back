package com.project.fitready.entity;

import com.project.fitready.dto.ReceitaRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "receita")
@Entity(name = "receita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String ingredientes;
    private String modoPreparo;
    private Long tempoPreparo;
    private Long calorias;
    private Long proteinas;
    private Long carboidratos;
    private Long gorduras;


    public Receita(ReceitaRequestDTO dto) {
        if (dto == null) {
            return;
        }

        this.nome = dto.nome();
        this.ingredientes = dto.ingredientes();
        this.modoPreparo = dto.modoPreparo();
        this.tempoPreparo = dto.tempoPreparo();
    }
}
