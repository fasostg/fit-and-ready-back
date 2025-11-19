package com.project.fitready.service;

import com.project.fitready.domain.TipoExercicio;
import com.project.fitready.dto.*;
import com.project.fitready.entity.Checkin;
import com.project.fitready.entity.Exercicio;
import com.project.fitready.entity.Treino;
import com.project.fitready.repository.CheckinRepository;
import com.project.fitready.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository repository;

    @Autowired
    private TipoExercicioService tipoExercicioService;

    @Autowired
    private TipoTreinoService tipoTreinoService;

    public List<Treino> buscarTodosTreinosPorUsuario() {
        return repository.findAll();
    }

    public void criarTreino(Treino treino) {
        repository.save(treino);
    }


    public Treino converterDTO(TreinoRequestDTO dto) {
        Treino treino = new Treino();

        treino.setNome(dto.nome());
        treino.setTipoTreino(tipoTreinoService.buscarPorId(dto.idTipoTreino()));
        treino.setExercicios(dto.exercicios().stream().map(this::converterExercicioDTO).toList());
        treino.setDataInicio(dto.dataInicio());

        return treino;
    }

    public Exercicio converterExercicioDTO(ExercicioDTO dto) {
        Exercicio exercicio = new Exercicio();

        exercicio.setTipoExercicio(tipoExercicioService.converterDTO(dto.tipoExercicio()));
        exercicio.setNumeroSeries(dto.numeroSeries());
        exercicio.setNumeroRepeticoes(dto.numeroRepeticoes());

        return exercicio;
    }
}
