package com.project.fitready.service;

import com.project.fitready.dto.ReceitaRequestDTO;
import com.project.fitready.entity.Receita;
import com.project.fitready.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

    public List<Receita> buscarTodasReceitas() {
        return repository.findAll();
    }

    public void cadastrarReceita(Receita receita) {
        repository.save(receita);
    }
}
