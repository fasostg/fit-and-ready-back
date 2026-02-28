package com.project.fitready.repository;

import com.project.fitready.dto.DadosHistoricoResponseDTO;
import com.project.fitready.entity.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {

    List<Checkin> findByCpfUsuarioOrderByIdDesc(String cpfUsuario);

    @Query("""
        SELECT new com.project.fitready.dto.DadosHistoricoResponseDTO(
            CAST(FUNCTION('to_char', c.data, 'DD/MM') AS string),
            SUM(c.tempoTreino)
        )
        FROM Checkin c
        WHERE c.data > :dataInicio
        GROUP BY c.data
        ORDER BY c.data ASC
    """)
    List<DadosHistoricoResponseDTO> buscarTempoTreinoPorDataApos(LocalDate dataInicio);

    @Query("""
        SELECT new com.project.fitready.dto.DadosHistoricoResponseDTO(
            CAST(FUNCTION('to_char', c.data, 'DD/MM') AS string),
            SUM(c.calorias)
        )
        FROM Checkin c
        WHERE c.data > :dataInicio
        GROUP BY c.data
        ORDER BY c.data ASC
    """)
    List<DadosHistoricoResponseDTO> buscarCaloriasTreinoPorDataApos(LocalDate dataInicio);

    @Query("""
        SELECT c
        FROM Checkin c
        WHERE c.data > :dataInicio
        ORDER BY c.data ASC
    """)
    List<Checkin> buscarPorDataApos(LocalDate dataInicio);
}
