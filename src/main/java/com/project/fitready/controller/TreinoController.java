package com.project.fitready.controller;

import com.project.fitready.dto.CheckinRequestDTO;
import com.project.fitready.dto.CheckinResponseDTO;
import com.project.fitready.dto.TreinoRequestDTO;
import com.project.fitready.dto.TreinoResponseDTO;
import com.project.fitready.entity.Checkin;
import com.project.fitready.entity.Treino;
import com.project.fitready.service.CheckinService;
import com.project.fitready.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("treino")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @GetMapping(path="/all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<TreinoResponseDTO> getAll() {
        return treinoService.buscarTodosTreinosPorUsuario().stream().map(TreinoResponseDTO::new).toList();
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void postTreino(@RequestBody TreinoRequestDTO treinoDTO) {
        Treino treino = treinoService.converterDTO(treinoDTO);
        treinoService.criarTreino(treino);
    }

}
