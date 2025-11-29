package com.project.fitready.repository;

import com.project.fitready.entity.Exercicio;
import com.project.fitready.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
}
