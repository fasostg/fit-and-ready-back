package com.project.fitready.service;

import com.project.fitready.dto.CheckinRequestDTO;
import com.project.fitready.dto.DadosHistoricoCompletoDTO;
import com.project.fitready.dto.DadosHistoricoDTO;
import com.project.fitready.dto.TipoExercicioDTO;
import com.project.fitready.entity.Checkin;
import com.project.fitready.entity.Treino;
import com.project.fitready.enums.IntensidadeEnum;
import com.project.fitready.enums.PeriodoEnum;
import com.project.fitready.json.DadosExerciciosJson;
import com.project.fitready.json.ExercicioJson;
import com.project.fitready.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CheckinService {

    @Autowired
    private CheckinRepository repository;

    @Autowired
    private IntensidadeService intensidadeService;

    @Autowired
    private TreinoService treinoService;

    public List<Checkin> buscarTodosCheckinsPorUsuario() {
        return repository.findAll();
    }

    public void cadastrarCheckin(Checkin checkin) {
        repository.save(checkin);
    }


    public Checkin converterDTO(CheckinRequestDTO checkinDTO) {
        Checkin checkin = new Checkin();

        checkin.setTempoTreino(checkinDTO.tempoTreino());
        checkin.setPeso(checkinDTO.peso());
        checkin.setData(LocalDate.now());
        checkin.setIntensidade(intensidadeService.buscarPorId(checkinDTO.idIntensidade()));
        checkin.setCalorias(calcularCalorias(checkin));

        Treino treino = treinoService.buscaPorId(checkinDTO.idTreino());
        checkin.setTreino(treino);
        ajustarDadosTreino(treino, checkinDTO);

        checkin.setDadosExercicios(criarDadosExerciciosJson(treino));

        return checkin;
    }

    // Cálculo baseado no MET (Equivalente Metabólico da Tarefa)
    // Calorias queimadas = MET x Peso corporal (kg) x Duração (horas)
    private Long calcularCalorias(Checkin checkin) {
        var tempoEmHoras = checkin.getTempoTreino() / 60.0;

        var metConstante = 3.5;
        if (Objects.equals(checkin.getIntensidade().getId(), IntensidadeEnum.MEDIA.getId())) {
            metConstante = 5.0;
        } else if (Objects.equals(checkin.getIntensidade().getId(), IntensidadeEnum.ALTA.getId())) {
            metConstante = 6.0;
        }

        return Math.round(metConstante * checkin.getPeso() * tempoEmHoras);
    }

    private void ajustarDadosTreino(Treino treino, CheckinRequestDTO checkinDTO) {
        if (checkinDTO.exercicios() == null || checkinDTO.exercicios().isEmpty()) {
            return;
        }

        treino.getExercicios().forEach(exercicio ->
            checkinDTO.exercicios().forEach(exercicioDTO -> {
                if (exercicio.getId().equals(exercicioDTO.id())) {
                    exercicio.setCarga(exercicioDTO.carga());
                    exercicio.setObservacao(exercicioDTO.observacao());
                }
            })
        );
    }

    private DadosExerciciosJson criarDadosExerciciosJson(Treino treino) {
        if (treino.getExercicios() == null || treino.getExercicios().isEmpty()) {
            return null;
        }

        List<ExercicioJson> exercicios = treino.getExercicios().stream().map(ExercicioJson::new).toList();
        return new DadosExerciciosJson(exercicios);
    }

    public List<DadosHistoricoDTO> buscarTempoTreinoPorPeriodo(PeriodoEnum periodo) {
        if (periodo == null) {
            throw new RuntimeException("Período de busca inválido.");
        }

        LocalDate dataInicio = calcularDataInicio(periodo);

        return repository.buscarTempoTreinoPorDataApos(dataInicio);
    }

    public List<DadosHistoricoDTO> buscarCaloriasTreinoPorPeriodo(PeriodoEnum periodo) {
        if (periodo == null) {
            throw new RuntimeException("Período de busca inválido.");
        }

        LocalDate dataInicio = calcularDataInicio(periodo);

        return repository.buscarCaloriasTreinoPorDataApos(dataInicio);
    }

    public List<DadosHistoricoCompletoDTO> buscarDadosExerciciosUsuario(PeriodoEnum periodo) {
        if (periodo == null) {
            throw new RuntimeException("Período de busca inválido.");
        }

        LocalDate dataInicio = calcularDataInicio(periodo);
        List<Checkin> checkins = repository.buscarPorDataApos(dataInicio);

        return checkins.stream()
                .map(checkin -> checkin.getDadosExercicios().getExercicios().stream()
                        .map(exercicioJson -> new DadosHistoricoCompletoDTO(
                                exercicioJson.getIdTipoExercicio(),
                                exercicioJson.getNomeTipoExercicio(),
                                checkin.getData().format(DateTimeFormatter.ofPattern("dd/MM")),
                                exercicioJson.getCarga()
                        ))
                        .toList())
                .flatMap(List::stream)
                .toList();
    }

    private LocalDate calcularDataInicio(PeriodoEnum periodo) {
        LocalDate diaAtual = LocalDate.now();

        return switch(periodo) {
            case PeriodoEnum.SEMANA -> diaAtual.minusDays(7);
            case PeriodoEnum.MES -> diaAtual.minusDays(30);
            case PeriodoEnum.ANO -> diaAtual.minusMonths(12);
            default -> throw new RuntimeException("Período de busca inválido.");
        };
    }

}
