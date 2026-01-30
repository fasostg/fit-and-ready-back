package com.project.fitready.repository;

import com.project.fitready.domain.TipoRefeicao;
import com.project.fitready.domain.TipoTreino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRefeicaoRepository extends JpaRepository<TipoRefeicao, Long> {
}
