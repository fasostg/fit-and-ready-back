package com.project.fitready.repository;

import com.project.fitready.dto.DadosHistoricoDTO;
import com.project.fitready.dto.TipoExercicioDTO;
import com.project.fitready.entity.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {

    @Query("""
        SELECT new com.project.fitready.dto.DadosHistoricoDTO(
            CAST(FUNCTION('to_char', c.data, 'DD/MM') AS string),
            SUM(c.tempoTreino)
        )
        FROM Checkin c
        WHERE c.data > :dataInicio
        GROUP BY c.data
        ORDER BY c.data ASC
    """)
    public List<DadosHistoricoDTO> buscarTempoTreinoPorDataApos(LocalDate dataInicio);

    @Query("""
        SELECT new com.project.fitready.dto.DadosHistoricoDTO(
            CAST(FUNCTION('to_char', c.data, 'DD/MM') AS string),
            SUM(c.calorias)
        )
        FROM Checkin c
        WHERE c.data > :dataInicio
        GROUP BY c.data
        ORDER BY c.data ASC
    """)
    public List<DadosHistoricoDTO> buscarCaloriasTreinoPorDataApos(LocalDate dataInicio);

    @Query("""
        SELECT c
        FROM Checkin c
        WHERE c.data > :dataInicio
        ORDER BY c.data ASC
    """)
    public List<Checkin> buscarPorDataApos(LocalDate dataInicio);

//    @Query("""
//        SELECT new com.project.fitready.dto.DadosHistoricoDTO(
//            CAST(FUNCTION('to_char', c.data, 'DD/MM') AS string),
//            SUM(c.calorias)
//        )
//        FROM Checkin c,
//             jsonb_array_elements(c.dadosExercicios->'exercicios') AS elem
//        WHERE c.data > :dataInicio
//          AND CAST(elem->>'idTipoExercicio' AS BIGINT) = :idTipoExercicio
//        GROUP BY c.data
//        ORDER BY c.data ASC
//    """)
//    public List<DadosHistoricoDTO> buscarCargaTreinoPorData(LocalDate dataInicio, Long idTipoExercicio);
//
//    @Query("""
//        SELECT DISTINCT(CAST(elem->>'idTipoExercicio' AS int))
//        FROM Checkin c,
//             jsonb_array_elements(c.dadosExercicios->'exercicios') AS elem
//    """)
//    public List<Long> buscarTiposExerciciosUsuario();
}
