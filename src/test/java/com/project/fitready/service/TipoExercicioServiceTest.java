package com.project.fitready.service;

import com.project.fitready.domain.GrupoMuscular;
import com.project.fitready.domain.TipoExercicio;
import com.project.fitready.dto.GrupoMuscularDTO;
import com.project.fitready.dto.TipoExercicioDTO;
import com.project.fitready.repository.TipoExercicioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TipoExercicioServiceTest {

    @Mock
    private TipoExercicioRepository repository;

    @InjectMocks
    private TipoExercicioService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarPorId_returnsEntity() {
        TipoExercicio tipoExercicio = new TipoExercicio();
        when(repository.findById(1L)).thenReturn(Optional.of(tipoExercicio));
        TipoExercicio result = service.buscarPorId(1L);
        assertEquals(tipoExercicio, result);
    }

    @Test
    void buscarPorId_notFound_throwsException() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> service.buscarPorId(2L));
    }

    @Test
    void buscarTodos_returnsAllEntities() {
        List<TipoExercicio> list = List.of(new TipoExercicio(), new TipoExercicio());
        when(repository.findAll()).thenReturn(list);
        List<TipoExercicio> result = service.buscarTodos();
        assertEquals(list, result);
    }

    @Test
    void converterDTO_setsFieldsCorrectly() {
        GrupoMuscularDTO grupoMuscularDTO = new GrupoMuscularDTO(10L, "Peito");
        TipoExercicioDTO dto = mock(TipoExercicioDTO.class);
        when(dto.id()).thenReturn(1L);
        when(dto.nome()).thenReturn("Supino");
        when(dto.grupoMuscular()).thenReturn(grupoMuscularDTO);

        TipoExercicio result = service.converterDTO(dto);
        assertEquals(1L, result.getId());
        assertEquals("Supino", result.getNome());
        assertEquals(10L, result.getGrupoMuscular().getId());
        assertEquals("Peito", result.getGrupoMuscular().getNome());
    }
}

