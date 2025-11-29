package com.project.fitready.service;

import com.project.fitready.domain.GrupoMuscular;
import com.project.fitready.domain.TipoExercicio;
import com.project.fitready.dto.TipoExercicioDTO;
import com.project.fitready.repository.GrupoMuscularRepository;
import com.project.fitready.repository.TipoExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoMuscularService {

    @Autowired
    private GrupoMuscularRepository repository;

    public List<GrupoMuscular> buscarTodos() {
        return repository.findAll();
    }
}
