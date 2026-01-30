package com.project.fitready.service;

import com.project.fitready.domain.Ingrediente;
import com.project.fitready.domain.UnidadeMedida;
import com.project.fitready.repository.IngredienteRepository;
import com.project.fitready.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository repository;

    public Ingrediente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Ingrediente> buscarTodos() {
        return repository.findAll();
    }
}
