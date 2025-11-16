package com.project.fitready.controller;

import com.project.fitready.dto.CheckinRequestDTO;
import com.project.fitready.dto.CheckinResponseDTO;
import com.project.fitready.entity.Checkin;
import com.project.fitready.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("checkin")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    @GetMapping(path="/all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<CheckinResponseDTO> getAll() {
        return checkinService.buscarTodosCheckinsPorUsuario().stream().map(CheckinResponseDTO::new).toList();
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void postCheckin(@RequestBody CheckinRequestDTO checkinDTO) {
        Checkin checkin = checkinService.converterDTO(checkinDTO);
        checkinService.cadastrarCheckin(checkin);
    }

}
