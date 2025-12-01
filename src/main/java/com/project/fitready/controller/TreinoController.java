package com.project.fitready.controller;

import com.project.fitready.dto.*;
import com.project.fitready.entity.Checkin;
import com.project.fitready.entity.Treino;
import com.project.fitready.service.*;
import jakarta.transaction.Transactional;
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
    public void postTreino(@RequestBody TreinoRequestDTO treinoDTO) {
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
    public void updateTreino(@RequestBody TreinoRequestDTO treinoDTO) {
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
