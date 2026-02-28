package com.project.fitready.service;

import com.project.fitready.domain.Ingrediente;
import com.project.fitready.domain.TipoRefeicao;
import com.project.fitready.dto.IngredienteDTO;
import com.project.fitready.dto.IngredienteReceitaDTO;
import com.project.fitready.dto.ReceitaRequestDTO;
import com.project.fitready.dto.TipoRefeicaoDTO;
import com.project.fitready.entity.Receita;
import com.project.fitready.repository.ReceitaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReceitaServiceTest {

    @Mock
    private ReceitaRepository repository;
    @Mock
    private IngredienteReceitaService ingredienteReceitaService;
    @Mock
    private IngredienteService ingredienteService;
    @Mock
    private TipoRefeicaoService tipoRefeicaoService;
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private ReceitaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarTodasReceitasPorUsuario_returnsReceitas() {
        String cpf = "12345678900";
        when(usuarioService.getCpfUsuarioLogado()).thenReturn(cpf);
        List<Receita> receitas = List.of(new Receita());
        when(repository.findByCpfUsuarioOrderByIdDesc(cpf)).thenReturn(receitas);
        List<Receita> result = service.buscarTodasReceitasPorUsuario();
        assertEquals(receitas, result);
    }

    @Test
    void buscarPorId_returnsReceita() {
        Receita receita = new Receita();
        when(repository.findById(1L)).thenReturn(Optional.of(receita));
        Receita result = service.buscarPorId(1L);
        assertEquals(receita, result);
    }

    @Test
    void buscarPorId_notFound_throwsException() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.buscarPorId(2L));
    }

    @Test
    void cadastrarReceita_savesReceita() {
        Receita receita = new Receita();
        service.cadastrarReceita(receita);
        verify(repository, times(1)).save(receita);
    }

    @Test
    void deletarReceita_deletesReceita() {
        Receita receita = new Receita();
        when(repository.findById(1L)).thenReturn(Optional.of(receita));
        service.deletarReceita(1L);
        verify(repository, times(1)).delete(receita);
    }

    @Test
    void atualizarReceita_updatesReceita() {
        Receita receita = new Receita();
        ReceitaRequestDTO dto = mock(ReceitaRequestDTO.class);
        when(dto.id()).thenReturn(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(receita));
        when(repository.save(receita)).thenReturn(receita);

        TipoRefeicaoDTO tipoRefeicaoDTO = mock(TipoRefeicaoDTO.class);
        when(dto.tipoRefeicao()).thenReturn(tipoRefeicaoDTO);
        when(tipoRefeicaoDTO.id()).thenReturn(1L);

        TipoRefeicao tipoRefeicao = new TipoRefeicao();
        when(tipoRefeicaoService.buscarPorId(anyLong())).thenReturn(tipoRefeicao);

        service.atualizarReceita(dto);
        verify(repository, times(1)).save(receita);
    }

    @Test
    void converterReceita_setsFieldsCorrectly() {
        ReceitaRequestDTO dto = mock(ReceitaRequestDTO.class);
        when(dto.nome()).thenReturn("Receita Teste");
        when(dto.modoPreparo()).thenReturn("Modo");
        when(dto.tempoPreparo()).thenReturn(10L);

        TipoRefeicaoDTO tipoRefeicaoDTO = mock(TipoRefeicaoDTO.class);
        when(dto.tipoRefeicao()).thenReturn(tipoRefeicaoDTO);
        when(tipoRefeicaoDTO.id()).thenReturn(1L);

        TipoRefeicao tipoRefeicao = mock(TipoRefeicao.class);
        when(tipoRefeicaoService.buscarPorId(1L)).thenReturn(tipoRefeicao);
        when(dto.ingredientesReceita()).thenReturn(List.of());
        when(usuarioService.getCpfUsuarioLogado()).thenReturn("12345678900");

        Receita receita = service.converterReceita(dto);
        assertEquals("Receita Teste", receita.getNome());
        assertEquals("Modo", receita.getModoPreparo());
        assertEquals(10L, receita.getTempoPreparo());
        assertEquals(tipoRefeicao, receita.getTipoRefeicao());
        assertEquals("12345678900", receita.getCpfUsuario());
    }

    @Test
    void converterReceita_nullDTO_returnsNull() {
        assertNull(service.converterReceita(null));
    }

    @Test
    void calcularAndSetarMacros_setsMacrosCorrectly() {
        Receita receita = new Receita();
        IngredienteReceitaDTO ingredienteReceitaDTO = mock(IngredienteReceitaDTO.class);
        IngredienteDTO ingredienteDTO = mock(IngredienteDTO.class);
        when(ingredienteReceitaDTO.ingrediente()).thenReturn(ingredienteDTO);
        when(ingredienteReceitaDTO.quantidade()).thenReturn(2.0);

        Ingrediente ingrediente = mock(Ingrediente.class);
        when(ingrediente.getId()).thenReturn(1L);
        when(ingredienteService.buscarPorId(1L)).thenReturn(ingrediente);
        when(ingrediente.getCalorias()).thenReturn(100.0);
        when(ingrediente.getProteinas()).thenReturn(10.0);
        when(ingrediente.getCarboidratos()).thenReturn(20.0);
        when(ingrediente.getGorduras()).thenReturn(5.0);

        ReceitaRequestDTO dto = mock(ReceitaRequestDTO.class);
        when(dto.ingredientesReceita()).thenReturn(List.of(ingredienteReceitaDTO));
        when(dto.nome()).thenReturn("Receita Teste");
        when(dto.modoPreparo()).thenReturn("Modo");
        when(dto.tempoPreparo()).thenReturn(10L);

        TipoRefeicaoDTO tipoRefeicaoDTO = mock(TipoRefeicaoDTO.class);
        when(dto.tipoRefeicao()).thenReturn(tipoRefeicaoDTO);
        when(tipoRefeicaoDTO.id()).thenReturn(1L);

        TipoRefeicao tipoRefeicao = mock(TipoRefeicao.class);
        when(tipoRefeicaoService.buscarPorId(1L)).thenReturn(tipoRefeicao);

        service.converterReceita(dto);
        assertEquals(200, receita.getCalorias());
        assertEquals(20, receita.getProteinas());
        assertEquals(40, receita.getCarboidratos());
        assertEquals(10, receita.getGorduras());
    }
}

