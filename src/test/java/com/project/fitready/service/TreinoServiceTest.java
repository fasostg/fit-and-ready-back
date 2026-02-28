package com.project.fitready.service;

import com.project.fitready.domain.GrupoMuscular;
import com.project.fitready.domain.TipoExercicio;
import com.project.fitready.domain.TipoTreino;
import com.project.fitready.dto.*;
import com.project.fitready.entity.Exercicio;
import com.project.fitready.entity.Treino;
import com.project.fitready.repository.TreinoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TreinoServiceTest {

    @Mock
    private TreinoRepository repository;
    @Mock
    private TipoExercicioService tipoExercicioService;
    @Mock
    private TipoTreinoService tipoTreinoService;
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private TreinoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarTodosTreinosPorUsuario_returnsTreinos() {
        String cpf = "12345678900";
        when(usuarioService.getCpfUsuarioLogado()).thenReturn(cpf);
        List<Treino> treinos = List.of(new Treino());
        when(repository.findByCpfUsuarioOrderByIdDesc(cpf)).thenReturn(treinos);
        List<Treino> result = service.buscarTodosTreinosPorUsuario();
        assertEquals(treinos, result);
    }

    @Test
    void buscaPorId_returnsTreino() {
        Treino treino = new Treino();
        when(repository.findById(1L)).thenReturn(Optional.of(treino));
        Treino result = service.buscaPorId(1L);
        assertEquals(treino, result);
    }

    @Test
    void buscaPorId_notFound_throwsException() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.buscaPorId(2L));
    }

    @Test
    void criarTreino_savesTreino() {
        TreinoRequestDTO dto = mock(TreinoRequestDTO.class);
        Treino treino = mock(Treino.class);
        when(usuarioService.getCpfUsuarioLogado()).thenReturn("12345678900");
        doReturn(treino).when(service).converterDTO(dto);
        service.criarTreino(dto);
        verify(repository, times(1)).save(any(Treino.class));
    }

    @Test
    void deletarTreino_setsDataFimAndSaves() {
        Treino treino = new Treino();
        when(repository.findById(1L)).thenReturn(Optional.of(treino));
        service.deletarTreino(1L);
        assertEquals(LocalDate.now(), treino.getDataFim());
        verify(repository, times(1)).save(treino);
    }

    @Test
    void atualizarTreino_updatesFieldsAndSaves() {
        Treino treino = new Treino();
        TreinoRequestDTO dto = mock(TreinoRequestDTO.class);
        when(dto.id()).thenReturn(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(treino));
        service.atualizarTreino(dto);
        verify(repository, times(1)).save(treino);
    }

    @Test
    void converterDTO_setsFieldsCorrectly() {
        TreinoRequestDTO dto = mock(TreinoRequestDTO.class);
        when(dto.nome()).thenReturn("Treino Teste");

        TipoTreinoDTO tipoTreino = new TipoTreinoDTO(1L, "Musculação");
        when(dto.tipoTreino()).thenReturn(tipoTreino);

        when(dto.exercicios()).thenReturn(List.of());
        when(dto.dataInicio()).thenReturn("25/02/2026");
        when(usuarioService.getCpfUsuarioLogado()).thenReturn("12345678900");

        Treino treino = service.converterDTO(dto);
        assertEquals("Treino Teste", treino.getNome());
        assertEquals("12345678900", treino.getCpfUsuario());
        assertEquals(LocalDate.of(2026, 2, 25), treino.getDataInicio());
    }

    @Test
    void converterExercicioDTO_setsFieldsCorrectly() {
        ExercicioDTO dto = mock(ExercicioDTO.class);

        GrupoMuscularDTO grupoMuscularDTO = new GrupoMuscularDTO(1L, "Peitoral");
        TipoExercicioDTO tipoExercicioDTO = new TipoExercicioDTO(1L, "Supino", grupoMuscularDTO);
        when(dto.tipoExercicio()).thenReturn(tipoExercicioDTO);
        when(dto.numeroSeries()).thenReturn(3L);
        when(dto.numeroRepeticoes()).thenReturn(12L);

        TipoExercicio tipoExercicio = new TipoExercicio(
                tipoExercicioDTO.id(),
                tipoExercicioDTO.nome(),
                new GrupoMuscular(grupoMuscularDTO.id(), grupoMuscularDTO.nome())
        );
        when(tipoExercicioService.converterDTO(tipoExercicioDTO)).thenReturn(tipoExercicio);

        Exercicio exercicio = service.converterExercicioDTO(dto);
        assertEquals(tipoExercicio, exercicio.getTipoExercicio());
        assertEquals(3, exercicio.getNumeroSeries());
        assertEquals(12, exercicio.getNumeroRepeticoes());
    }
}

