package com.project.fitready.controller;

import com.project.fitready.dto.ReceitaRequestDTO;
import com.project.fitready.dto.ReceitaResponseDTO;
import com.project.fitready.dto.TipoRefeicaoDTO;
import com.project.fitready.dto.TipoTreinoDTO;
import com.project.fitready.entity.Receita;
import com.project.fitready.ia.service.GeminiService;
import com.project.fitready.ia.service.GroqService;
import com.project.fitready.ia.service.HuggingFaceService;
import com.project.fitready.service.ReceitaService;
import com.project.fitready.service.TipoRefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("nutricao")
public class NutricaoController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private TipoRefeicaoService tipoRefeicaoService;

    @Autowired
    private HuggingFaceService huggingFaceService;

    @Autowired
    private GroqService groqService;

    @Autowired
    private GeminiService geminiService;

    @GetMapping(path="/all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<ReceitaResponseDTO> getAll() {
        var prompt = """
                Você é um nutricionista.
                
                Calcule os macronutrientes totais dos ingredientes abaixo.
        
                REGRAS:
                - Converta todas as medidas para gramas
                - Use valores médios
                - Retorne APENAS JSON válido
                - Não escreva explicações
        
                Formato exato:
                {
                "calorias": number,
                "proteinas": number,
                "carboidratos": number,
                "gorduras": number
                }
        
                Ingredientes:
                - Peito de frango: 200g
                - Arroz branco cozido: 150g
                - Azeite de oliva: 1 colher de sopa
                
                """;
        geminiService.gerarMacros(prompt);
        huggingFaceService.gerarMacros(prompt);

        return receitaService.buscarTodasReceitas().stream()
                .map(ReceitaResponseDTO::new)
                .toList();
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void postReceita(@RequestBody ReceitaRequestDTO receitaDTO) {
        Receita receita = receitaService.converterReceita(receitaDTO);
        receitaService.cadastrarReceita(receita);
    }

    @GetMapping(path="/tipos-refeicao")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<TipoRefeicaoDTO> getTiposRefeicao() {
        return tipoRefeicaoService.buscarTodos().stream().map(TipoRefeicaoDTO::new).toList();
    }



}
