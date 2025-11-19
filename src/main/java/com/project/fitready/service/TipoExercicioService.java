package com.project.fitready.service;

import com.project.fitready.domain.TipoExercicio;
import com.project.fitready.dto.ExercicioDTO;
import com.project.fitready.dto.TipoExercicioDTO;
import com.project.fitready.entity.Exercicio;
import com.project.fitready.entity.Treino;
import com.project.fitready.repository.TipoExercicioRepository;
import com.project.fitready.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoExercicioService {

    @Autowired
    private TipoExercicioRepository repository;

    public TipoExercicio buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public TipoExercicio converterDTO(TipoExercicioDTO dto) {
        TipoExercicio tipoExercicio = new TipoExercicio();

        tipoExercicio.setNome(dto.nome());
        tipoExercicio.setGrupoMuscular(null);

        return tipoExercicio;
    }
}
