package com.project.fitready.service;

import com.project.fitready.domain.TipoRefeicao;
import com.project.fitready.domain.UnidadeMedida;
import com.project.fitready.repository.TipoRefeicaoRepository;
import com.project.fitready.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository repository;

    public UnidadeMedida buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<UnidadeMedida> buscarTodos() {
        return repository.findAll();
    }
}
