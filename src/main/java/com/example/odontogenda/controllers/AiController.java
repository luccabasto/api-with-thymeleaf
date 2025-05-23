package com.example.odontogenda.controllers;

import com.example.odontogenda.ai.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ia")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    /**
     * Endpoint: GET /ia/sugestao
     * Parâmetro opcional: contexto (prompt para a IA)
     * Retorna: texto gerado pela IA
     */
    @GetMapping("/sugestao")
    public String sugestao(
            @RequestParam(value = "contexto",
                    defaultValue = "Preciso de sugestão de horários para agendamento odontológico") String contexto) {
        return aiService.gerarSugestaoHorario(contexto);
    }
}
