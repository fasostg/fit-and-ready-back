package com.project.fitready.repository;

import com.project.fitready.domain.TipoExercicio;
import com.project.fitready.domain.TipoTreino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoTreinoRepository extends JpaRepository<TipoTreino, Long> {
}
