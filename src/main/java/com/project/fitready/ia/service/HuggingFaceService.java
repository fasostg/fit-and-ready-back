package com.project.fitready.ia.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Map;

@Service
public class HuggingFaceService {

    private final WebClient webClient;

    public HuggingFaceService(@Value("${huggingface.token}") String token) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api-inference.huggingface.co/v1/chat/completions")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String gerarMacros(String prompt) {
        Map<String, Object> body = Map.of(
                "model", "mistralai/Mistral-7B-Instruct-v0.2",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                ),
                "temperature", 0.2
        );

        try {
            return webClient.post()
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode().value() == 503) {
                // Model is loading, retry once after short wait
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {}
                return webClient.post()
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
            }
            throw e;
        }
    }
}
