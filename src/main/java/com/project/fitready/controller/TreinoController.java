package com.project.fitready.controller;

import com.project.fitready.dto.*;
import com.project.fitready.service.GrupoMuscularService;
import com.project.fitready.service.TipoExercicioService;
import com.project.fitready.service.TipoTreinoService;
import com.project.fitready.service.TreinoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("treino")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private TipoTreinoService tipoTreinoService;

    @Autowired
    private GrupoMuscularService grupoMuscularService;

    @Autowired
    private TipoExercicioService tipoExercicioService;

    @GetMapping(path="/all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<TreinoResponseDTO> getAll() {
        return treinoService.buscarTodosTreinosPorUsuario().stream().map(TreinoResponseDTO::new).toList();
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Transactional
    public void postTreino(@RequestBody @Valid TreinoRequestDTO treinoDTO) {
        treinoService.criarTreino(treinoDTO);
    }

    @DeleteMapping(path="/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Transactional
    public void deleteTreino(@PathVariable Long id) {
        treinoService.deletarTreino(id);
    }

    @PatchMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Transactional
    public void updateTreino(@RequestBody @Valid TreinoRequestDTO treinoDTO) {
        treinoService.atualizarTreino(treinoDTO);
    }

    @GetMapping(path="/tipos-treino")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<TipoTreinoDTO> getTiposTreino() {
        return tipoTreinoService.buscarTodos().stream().map(TipoTreinoDTO::new).toList();
    }

    @GetMapping(path="/grupos-musculares")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<GrupoMuscularDTO> getGruposMusculares() {
        return grupoMuscularService.buscarTodos().stream().map(GrupoMuscularDTO::new).toList();
    }

    @GetMapping(path="/tipos-exercicios")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<TipoExercicioDTO> getTiposExercicios() {
        return tipoExercicioService.buscarTodos().stream().map(TipoExercicioDTO::new).toList();
    }
}
