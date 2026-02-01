package com.project.fitready.ia.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GroqService {

    private final WebClient client;

    public GroqService(@Value("${groq.token}") String token) {
        this.client = WebClient.builder()
                .baseUrl("https://api.groq.com/openai/v1/chat/completions")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String gerar(String prompt) {

        Map<String, Object> body = Map.of(
                "model", "llama3-8b-8192",
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", prompt   // MUST be String
                        )
                )
        );

        return client.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}