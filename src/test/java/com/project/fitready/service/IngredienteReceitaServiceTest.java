package com.project.fitready.service;

import com.project.fitready.domain.Ingrediente;
import com.project.fitready.dto.IngredienteDTO;
import com.project.fitready.dto.IngredienteReceitaDTO;
import com.project.fitready.entity.IngredienteReceita;
import com.project.fitready.entity.Receita;
import com.project.fitready.repository.IngredienteReceitaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredienteReceitaServiceTest {

    @Mock
    private IngredienteReceitaRepository repository;

    @Mock
    private IngredienteService ingredienteService;

    @InjectMocks
    private IngredienteReceitaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarPorId_returnsEntity() {
        IngredienteReceita ingredienteReceita = new IngredienteReceita();
        when(repository.findById(1L)).thenReturn(Optional.of(ingredienteReceita));

        IngredienteReceita result = service.buscarPorId(1L);
        assertEquals(ingredienteReceita, result);
    }

    @Test
    void buscarPorId_notFound_throwsException() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> service.buscarPorId(2L));
    }

    @Test
    void buscarTodos_returnsAllEntities() {
        List<IngredienteReceita> list = List.of(new IngredienteReceita(), new IngredienteReceita());
        when(repository.findAll()).thenReturn(list);

        List<IngredienteReceita> result = service.buscarTodos();
        assertEquals(list, result);
    }

    @Test
    void converterIngredienteReceita_setsFieldsCorrectly() {
        Receita receita = new Receita();

        IngredienteReceitaDTO dto = mock(IngredienteReceitaDTO.class);
        when(dto.quantidade()).thenReturn(2.5);

        IngredienteDTO ingredienteDTO = new IngredienteDTO(1L, "Farinha de Trigo", 1.0, 1.0, 1.0, 1.0);
        when(dto.ingrediente()).thenReturn(ingredienteDTO);

        Ingrediente ingrediente = new Ingrediente(1L, "Farinha de Trigo", 1.0, 1.0, 1.0, 1.0);
        when(ingredienteService.buscarPorId(1L)).thenReturn(ingrediente);

        IngredienteReceita result = service.converterIngredienteReceita(receita, dto);
        assertEquals(2.5, result.getQuantidade());
        assertEquals(ingrediente, result.getIngrediente());
        assertEquals(receita, result.getReceita());
    }
}

