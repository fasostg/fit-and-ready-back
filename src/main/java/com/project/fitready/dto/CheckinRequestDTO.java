package com.project.fitready.dto;

import java.time.LocalDateTime;

public record CheckinRequestDTO(Long idTreino, Long tempoTreino, Long calorias, Double peso) {

}
