package com.restaurante.mscozinha.infra.mqueues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restaurante.mscozinha.application.converter.ConverterToJson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Data
@AllArgsConstructor
public class WebhookSubscriber {

    private final ConverterToJson converterToJson;

    private String url;
    private String token;

    public void notificar(String statusPedido) throws JsonProcessingException {
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .header("Authorization", "Bearer " + token)
                    .POST(HttpRequest.BodyPublishers.ofString(converterToJson.convertToJson(statusPedido)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200 ){
                throw new RuntimeException("Erro ao enviar webhook: " + response.statusCode());
            }
        } catch  (Exception e){
            throw new RuntimeException("Falha ao notificar webhook", e);
        }
    }
}
