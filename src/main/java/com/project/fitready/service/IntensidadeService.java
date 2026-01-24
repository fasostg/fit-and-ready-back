package com.project.fitready.service;

import com.project.fitready.domain.Intensidade;
import com.project.fitready.domain.TipoTreino;
import com.project.fitready.repository.IntensidadeRepository;
import com.project.fitready.repository.TipoTreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntensidadeService {

    @Autowired
    private IntensidadeRepository repository;

    public Intensidade buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Intensidade> buscarTodos() {
        return repository.findAll();
    }
}
