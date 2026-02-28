package com.project.fitready.repository;

import com.project.fitready.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findByCpfUsuarioOrderByIdDesc(String cpfUsuario);
}
