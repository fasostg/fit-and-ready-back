package com.project.fitready.service;

import com.project.fitready.dto.CheckinRequestDTO;
import com.project.fitready.entity.Checkin;
import com.project.fitready.entity.Treino;
import com.project.fitready.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository repository;

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
        checkin.setCalorias(checkinDTO.calorias());
        checkin.setData(LocalDateTime.now());

        //Treino treino = treinoService.buscarTreinoPorId(checkinDTO.idTreino());
        //checkin.setTreino();
        return checkin;
    }
}
