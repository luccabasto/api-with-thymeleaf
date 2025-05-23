package com.example.odontogenda.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /** Gera sugestão de horário a partir de um prompt */
    public String gerarSugestaoHorario(String promptText) {
        // Ajuste para usar Prompt.ofString() em vez de .prompt(String)
        ChatClientRequest request = ChatClientRequest.builder()
                .prompt(org.springframework.ai.openai.Prompt.ofString(promptText))
                .build();

        ChatClientResponse response = this.chatClient.completion(request);
        
        return response.choices().get(0).text();
    }
}
