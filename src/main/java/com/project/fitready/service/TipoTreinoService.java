package com.project.fitready.service;

import com.project.fitready.domain.TipoExercicio;
import com.project.fitready.domain.TipoTreino;
import com.project.fitready.dto.TipoExercicioDTO;
import com.project.fitready.repository.TipoExercicioRepository;
import com.project.fitready.repository.TipoTreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoTreinoService {

    @Autowired
    private TipoTreinoRepository repository;

    public TipoTreino buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
