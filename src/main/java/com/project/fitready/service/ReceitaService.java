package com.project.fitready.service;

import com.project.fitready.domain.Ingrediente;
import com.project.fitready.domain.TipoRefeicao;
import com.project.fitready.dto.IngredienteReceitaDTO;
import com.project.fitready.dto.ReceitaRequestDTO;
import com.project.fitready.entity.IngredienteReceita;
import com.project.fitready.entity.Receita;
import com.project.fitready.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

    @Autowired
    private IngredienteReceitaService ingredienteReceitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private TipoRefeicaoService tipoRefeicaoService;

    public List<Receita> buscarTodasReceitas() {
        return repository.findAll();
    }

    public Receita buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    public void cadastrarReceita(Receita receita) {
        repository.save(receita);
    }

    public void deletarReceita(Long id) {
        Receita receita = buscarPorId(id);
        repository.delete(receita);
    }

    public void atualizarReceita(ReceitaRequestDTO receitaDTO) {
        Receita receita = buscarPorId(receitaDTO.id());
        setarCamposReceita(receita, receitaDTO);

        repository.save(receita);
    }

    public Receita converterReceita(ReceitaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Receita receita = new Receita();
        setarCamposReceita(receita, dto);

        return receita;
    }

    private void setarCamposReceita(Receita receita, ReceitaRequestDTO dto) {
        receita.setNome(dto.nome());
        receita.setIngredientesReceita(
            dto.ingredientesReceita().stream()
                .map(i -> ingredienteReceitaService.converterIngredienteReceita(receita, i))
                .toList()
        );
        receita.setModoPreparo(dto.modoPreparo());
        receita.setTempoPreparo(dto.tempoPreparo());

        TipoRefeicao tipoRefeicao = tipoRefeicaoService.buscarPorId(dto.tipoRefeicao().id());
        receita.setTipoRefeicao(tipoRefeicao);

        calcularAndSetarMacros(receita, dto);
    }

    private void calcularAndSetarMacros(Receita receita, ReceitaRequestDTO dto) {
        double calorias = 0.0;
        double proteinas = 0.0;
        double carboidratos = 0.0;
        double gorduras = 0.0;

        for (IngredienteReceitaDTO ingredienteReceita : dto.ingredientesReceita()) {
            Ingrediente ingrediente = ingredienteService.buscarPorId(ingredienteReceita.ingrediente().id());
            var quantidade = ingredienteReceita.quantidade();

            calorias += ingrediente.getCalorias() * quantidade;
            proteinas += ingrediente.getProteinas() * quantidade;
            carboidratos += ingrediente.getCarboidratos() * quantidade;
            gorduras += ingrediente.getGorduras() * quantidade;
        }

        receita.setCalorias(Math.round(calorias));
        receita.setProteinas(Math.round(proteinas));
        receita.setCarboidratos(Math.round(carboidratos));
        receita.setGorduras(Math.round(gorduras));
    }

}
