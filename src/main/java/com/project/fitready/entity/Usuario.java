package com.project.fitready.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(unique = true)
    private String cpf;
    private String senha;
}
