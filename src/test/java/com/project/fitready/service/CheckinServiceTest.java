package com.project.fitready.service;

import com.project.fitready.domain.Intensidade;
import com.project.fitready.dto.CheckinRequestDTO;
import com.project.fitready.dto.DadosHistoricoCompletoResponseDTO;
import com.project.fitready.dto.DadosHistoricoResponseDTO;
import com.project.fitready.entity.Checkin;
import com.project.fitready.entity.Treino;
import com.project.fitready.enums.IntensidadeEnum;
import com.project.fitready.enums.PeriodoEnum;
import com.project.fitready.json.DadosExerciciosJson;
import com.project.fitready.repository.CheckinRepository;
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

class CheckinServiceTest {

    @Mock
    private CheckinRepository repository;
    @Mock
    private IntensidadeService intensidadeService;
    @Mock
    private TreinoService treinoService;
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private CheckinService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarTodosCheckinsPorUsuario_returnsCheckins() {
        String cpf = "12345678900";
        when(usuarioService.getCpfUsuarioLogado()).thenReturn(cpf);

        List<Checkin> checkins = List.of(new Checkin());
        when(repository.findByCpfUsuarioOrderByIdDesc(cpf)).thenReturn(checkins);

        List<Checkin> result = service.buscarTodosCheckinsPorUsuario();
        assertEquals(checkins, result);
    }

    @Test
    void cadastrarCheckin_savesCheckin() {
        Checkin checkin = new Checkin();
        service.cadastrarCheckin(checkin);
        verify(repository, times(1)).save(checkin);
    }

    @Test
    void converterDTO_createsCheckinFromDTO() {
        CheckinRequestDTO dto = mock(CheckinRequestDTO.class);
        when(dto.tempoTreino()).thenReturn(60L);
        when(dto.peso()).thenReturn(70.0);
        when(dto.idIntensidade()).thenReturn(IntensidadeEnum.BAIXA.getId());
        when(dto.idTreino()).thenReturn(1L);
        when(dto.exercicios()).thenReturn(List.of());

        Intensidade intensidade = new Intensidade(1L, "Baixa");
        when(intensidadeService.buscarPorId(IntensidadeEnum.BAIXA.getId())).thenReturn(intensidade);

        Treino treino = mock(Treino.class);
        when(treinoService.buscaPorId(1L)).thenReturn(treino);
        when(usuarioService.getCpfUsuarioLogado()).thenReturn("12345678900");
        when(treino.getExercicios()).thenReturn(List.of());

        Checkin checkin = service.converterDTO(dto);
        assertEquals(60, checkin.getTempoTreino());
        assertEquals(70.0, checkin.getPeso());
        assertEquals(intensidade, checkin.getIntensidade());
        assertEquals(treino, checkin.getTreino());
        assertEquals("12345678900", checkin.getCpfUsuario());
    }

    @Test
    void buscarTempoTreinoPorPeriodo_returnsHistorico() {
        when(repository.buscarTempoTreinoPorDataApos(any())).thenReturn(List.of());

        List<DadosHistoricoResponseDTO> result = service.buscarTempoTreinoPorPeriodo(PeriodoEnum.SEMANA);
        assertNotNull(result);
    }

    @Test
    void buscarCaloriasTreinoPorPeriodo_returnsHistorico() {
        when(repository.buscarCaloriasTreinoPorDataApos(any())).thenReturn(List.of());

        List<DadosHistoricoResponseDTO> result = service.buscarCaloriasTreinoPorPeriodo(PeriodoEnum.SEMANA);
        assertNotNull(result);
    }

    @Test
    void buscarDadosExerciciosUsuario_returnsHistoricoCompleto() {
        Checkin checkin = mock(Checkin.class);
        DadosExerciciosJson dados = mock(DadosExerciciosJson.class);
        when(repository.buscarPorDataApos(any())).thenReturn(List.of(checkin));
        when(checkin.getDadosExercicios()).thenReturn(dados);
        when(dados.getExercicios()).thenReturn(List.of());

        List<DadosHistoricoCompletoResponseDTO> result = service.buscarDadosExerciciosUsuario(PeriodoEnum.SEMANA);
        assertNotNull(result);
    }

    @Test
    void buscarTempoTreinoPorPeriodo_invalidPeriodo_throwsException() {
        assertThrows(RuntimeException.class, () -> service.buscarTempoTreinoPorPeriodo(null));
    }

    @Test
    void buscarCaloriasTreinoPorPeriodo_invalidPeriodo_throwsException() {
        assertThrows(RuntimeException.class, () -> service.buscarCaloriasTreinoPorPeriodo(null));
    }

    @Test
    void buscarDadosExerciciosUsuario_invalidPeriodo_throwsException() {
        assertThrows(RuntimeException.class, () -> service.buscarDadosExerciciosUsuario(null));
    }
}

