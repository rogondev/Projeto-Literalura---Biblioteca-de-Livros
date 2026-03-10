package com.alura.literalura.service;

// IMPORTANTE: Use estes pacotes específicos
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados {
    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            // Agora que o import acima existe, este erro some
            throw new RuntimeException("Erro ao converter JSON", e);
        }
    }

}