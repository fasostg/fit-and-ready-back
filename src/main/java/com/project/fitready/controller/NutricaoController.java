package com.project.fitready.controller;

import com.project.fitready.dto.*;
import com.project.fitready.entity.Receita;
import com.project.fitready.service.IngredienteService;
import com.project.fitready.service.ReceitaService;
import com.project.fitready.service.TipoRefeicaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("nutricao")
public class NutricaoController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private TipoRefeicaoService tipoRefeicaoService;

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping(path="/all")
    public List<ReceitaResponseDTO> getAll() {
        return receitaService.buscarTodasReceitasPorUsuario().stream()
            .map(ReceitaResponseDTO::new)
            .toList();
    }

    @DeleteMapping(path="/{id}")
    @Transactional
    public void deleteReceita(@PathVariable Long id) { receitaService.deletarReceita(id); }

    @PostMapping
    @Transactional
    public void postReceita(@RequestBody @Valid ReceitaRequestDTO receitaDTO) {
        Receita receita = receitaService.converterReceita(receitaDTO);
        receitaService.cadastrarReceita(receita);
    }

    @PatchMapping
    @Transactional
    public void updateTreino(@RequestBody @Valid ReceitaRequestDTO receitaDTO) {
        receitaService.atualizarReceita(receitaDTO);
    }

    @GetMapping(path="/tipos-refeicao")
    public List<TipoRefeicaoDTO> getTiposRefeicao() {
        return tipoRefeicaoService.buscarTodos().stream().map(TipoRefeicaoDTO::new).toList();
    }

    @GetMapping(path="/ingredientes")
    public List<IngredienteDTO> getIngredientes() {
        return ingredienteService.buscarTodos().stream().map(IngredienteDTO::new).toList();
    }

}
