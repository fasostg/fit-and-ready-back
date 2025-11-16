package com.project.fitready.dto;

import com.project.fitready.entity.Checkin;

import java.time.LocalDateTime;

public record CheckinResponseDTO(Long id, String nomeTreino, Long tempoTreino, Long calorias, LocalDateTime data) {

    public CheckinResponseDTO(Checkin checkin) {
        this(checkin.getId(),
             "nomeTreinoMock",
             //checkin.getTreino().getNome(),
             checkin.getTempoTreino(),
             checkin.getCalorias(),
             checkin.getData());
    }
}
