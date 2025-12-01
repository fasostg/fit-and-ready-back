package com.project.fitready.service;

import com.project.fitready.domain.TipoExercicio;
import com.project.fitready.dto.*;
import com.project.fitready.entity.Checkin;
import com.project.fitready.entity.Exercicio;
import com.project.fitready.entity.Treino;
import com.project.fitready.repository.CheckinRepository;
import com.project.fitready.repository.ExercicioRepository;
import com.project.fitready.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository repository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private TipoExercicioService tipoExercicioService;

    @Autowired
    private TipoTreinoService tipoTreinoService;

    public List<Treino> buscarTodosTreinosPorUsuario() {
        return repository.findAll();
    }

    public Treino buscaPorId(Long idTreino) {
        return repository.findById(idTreino).orElseThrow(() -> new RuntimeException("Treino não encontrado"));
    }

    public void criarTreino(TreinoRequestDTO treinoDTO) {
        Treino treino = converterDTO(treinoDTO);
        repository.save(treino);
    }

    public void deletarTreino(Long id) {
        Treino treino = buscaPorId(id);
        treino.setDataFim(LocalDate.now());
        repository.save(treino);
    }

    public void atualizarTreino(TreinoRequestDTO treinoDTO) {
        Treino treino = buscaPorId(treinoDTO.id());

        setarCamposTreino(treino, treinoDTO);
        repository.save(treino);
    }

    public Treino converterDTO(TreinoRequestDTO dto) {
        Treino treino = new Treino();

        setarCamposTreino(treino, dto);
        return treino;
    }

    private void setarCamposTreino(Treino treino, TreinoRequestDTO dto) {
        treino.setNome(dto.nome());
        treino.setTipoTreino(tipoTreinoService.buscarPorId(dto.tipoTreino().id()));
        treino.setExercicios(dto.exercicios().stream().map(exercicioDTO -> {
            Exercicio exercicio = converterExercicioDTO(exercicioDTO);
            exercicio.setTreino(treino);
            return exercicio;
        }).toList());
        treino.setDataInicio(LocalDate.parse(dto.dataInicio(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public Exercicio converterExercicioDTO(ExercicioDTO dto) {
        Exercicio exercicio = new Exercicio();

        exercicio.setTipoExercicio(tipoExercicioService.converterDTO(dto.tipoExercicio()));
        exercicio.setNumeroSeries(dto.numeroSeries());
        exercicio.setNumeroRepeticoes(dto.numeroRepeticoes());

        return exercicio;
    }
}
