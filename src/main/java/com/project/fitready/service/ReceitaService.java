package com.project.fitready.service;

import com.project.fitready.dto.ReceitaRequestDTO;
import com.project.fitready.entity.IngredienteReceita;
import com.project.fitready.entity.Receita;
import com.project.fitready.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

    @Autowired
    private IngredienteReceitaService ingredienteReceitaService;

    public List<Receita> buscarTodasReceitas() {
        return repository.findAll();
    }

    public void cadastrarReceita(Receita receita) {
        repository.save(receita);
    }

    public Receita converterReceita(ReceitaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Receita receita = new Receita();

        receita.setNome(dto.nome());
        receita.setIngredientesReceita(
            dto.ingredientesReceita().stream()
                .map(i -> ingredienteReceitaService.converterIngredienteReceita(receita, i))
                .toList()
        );
        receita.setModoPreparo(dto.modoPreparo());
        receita.setTempoPreparo(dto.tempoPreparo());

        return receita;
    }
}
