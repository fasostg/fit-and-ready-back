package com.project.fitready.dto;

import com.project.fitready.entity.Checkin;

import java.time.LocalDate;

public record CheckinResponseDTO(Long id, String nomeTreino, Long tempoTreino, Long calorias, LocalDate dataTreino) {

    public CheckinResponseDTO(Checkin checkin) {
        this(checkin.getId(),
             checkin.getTreino() != null ? checkin.getTreino().getNome() : "Nome não disponível",
             checkin.getTempoTreino(),
             checkin.getCalorias(),
             checkin.getData());
    }
}
