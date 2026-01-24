package com.project.fitready.controller;

import com.project.fitready.dto.*;
import com.project.fitready.entity.Checkin;
import com.project.fitready.enums.PeriodoEnum;
import com.project.fitready.service.CheckinService;
import com.project.fitready.service.IntensidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("checkin")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    @Autowired
    private IntensidadeService intensidadeService;

    @GetMapping(path="/all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<CheckinResponseDTO> getAll() {
        return checkinService.buscarTodosCheckinsPorUsuario().stream().map(CheckinResponseDTO::new).toList();
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Void> postCheckin(@RequestBody CheckinRequestDTO checkinDTO) {
        Checkin checkin = checkinService.converterDTO(checkinDTO);
        checkinService.cadastrarCheckin(checkin);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path="/intensidades")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<IntensidadeDTO> getIntensidades() {
        return intensidadeService.buscarTodos().stream().map(IntensidadeDTO::new).toList();
    }

    @GetMapping(path="/tempo-treino")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<DadosHistoricoDTO> getTempoTreino(@RequestParam String periodo) {
        return checkinService.buscarTempoTreinoPorPeriodo(PeriodoEnum.getByName(periodo));
    }

    @GetMapping(path="/calorias-treino")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<DadosHistoricoDTO> getCaloriasTreino(@RequestParam String periodo) {
        return checkinService.buscarCaloriasTreinoPorPeriodo(PeriodoEnum.getByName(periodo));
    }

    @GetMapping(path="/dados-exercicios")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<DadosHistoricoCompletoDTO> getDadosExercicios(@RequestParam String periodo) {
        return checkinService.buscarDadosExerciciosUsuario(PeriodoEnum.getByName(periodo));
    }

//    @GetMapping(path="/carga-treino")
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    public List<DadosHistoricoDTO> getCargaPorExercicio(@RequestParam Long idTipoExercicio) {
//        return checkinService.buscarCargaPorTipoExercicio(idTipoExercicio);
//    }

}
