package com.project.fitready.repository;

import com.project.fitready.domain.GrupoMuscular;
import com.project.fitready.domain.TipoExercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoMuscularRepository extends JpaRepository<GrupoMuscular, Long> {
}
