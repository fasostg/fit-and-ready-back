package com.project.fitready.service;

import com.project.fitready.domain.Ingrediente;
import com.project.fitready.dto.IngredienteReceitaDTO;
import com.project.fitready.entity.IngredienteReceita;
import com.project.fitready.entity.Receita;
import com.project.fitready.repository.IngredienteReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteReceitaService {

    @Autowired
    private IngredienteReceitaRepository repository;

    @Autowired
    private IngredienteService ingredienteService;

    public IngredienteReceita buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<IngredienteReceita> buscarTodos() {
        return repository.findAll();
    }

    public IngredienteReceita converterIngredienteReceita(Receita receita, IngredienteReceitaDTO dto) {
        IngredienteReceita ingredienteReceita = new IngredienteReceita();

        ingredienteReceita.setQuantidade(dto.quantidade());

        Ingrediente ingrediente = ingredienteService.buscarPorId(dto.ingrediente().id());
        ingredienteReceita.setIngrediente(ingrediente);

        ingredienteReceita.setReceita(receita);
        return ingredienteReceita;
    }
}
