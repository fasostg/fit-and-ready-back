package com.project.fitready.repository;

import com.project.fitready.domain.Ingrediente;
import com.project.fitready.entity.IngredienteReceita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteReceitaRepository extends JpaRepository<IngredienteReceita, Long> {
}
