package com.project.fitready.repository;

import com.project.fitready.entity.Checkin;
import com.project.fitready.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
}
