package com.project.fitready.service;

import com.project.fitready.domain.TipoRefeicao;
import com.project.fitready.domain.TipoTreino;
import com.project.fitready.repository.TipoRefeicaoRepository;
import com.project.fitready.repository.TipoTreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoRefeicaoService {

    @Autowired
    private TipoRefeicaoRepository repository;

    public TipoRefeicao buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<TipoRefeicao> buscarTodos() {
        return repository.findAll();
    }
}
