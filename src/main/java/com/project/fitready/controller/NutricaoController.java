package com.project.fitready.controller;

import com.project.fitready.dto.ReceitaRequestDTO;
import com.project.fitready.dto.ReceitaResponseDTO;
import com.project.fitready.entity.Receita;
import com.project.fitready.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("nutricao")
public class NutricaoController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping(path="/all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<ReceitaResponseDTO> getAll() {
        List<ReceitaResponseDTO> receitas = receitaService.buscarTodasReceitas().stream()
                .map(ReceitaResponseDTO::new)
                .toList();

        return receitas;
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void postReceita(@RequestBody ReceitaRequestDTO receitaDTO) {
        Receita receita = new Receita(receitaDTO);
        receitaService.cadastrarReceita(receita);
    }

}
