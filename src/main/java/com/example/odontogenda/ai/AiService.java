package com.example.odontogenda.ai;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.model.chat.CompletionRequest;
import org.springframework.ai.model.chat.CompletionResponse;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /** Gera sugestão de horário a partir de um prompt */
    public String gerarSugestaoHorario(String prompt) {
        // monta a requisição
        CompletionRequest request = CompletionRequest.builder()
                .prompt(prompt)
                .build();
        CompletionResponse response = this.chatClient.completion(request);
        return response.getChoices().get(0).getText();
    }
}
