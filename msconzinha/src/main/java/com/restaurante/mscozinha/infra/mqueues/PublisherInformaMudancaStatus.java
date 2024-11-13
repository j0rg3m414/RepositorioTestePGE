package com.restaurante.mscozinha.infra.mqueues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restaurante.mscozinha.application.converter.ConverterToJson;
import com.restaurante.mscozinha.domain.model.PedidoGetRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublisherInformaMudancaStatus {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoMudancaStatus;
    private final Queue queueRetiradaPedidoEntregueFila;
    private final ConverterToJson converterJson;

    public void enviarNovoStatusPedido(PedidoGetRequest dadosFila) throws JsonProcessingException {
        var json = converterJson.convertToJson(dadosFila);
        rabbitTemplate.convertAndSend(queueEmissaoMudancaStatus.getName(),json);
        log.info("--> Mensagem enviada para fila de mudança de status do pedido...");
    }

    public void enviarSolicitacaoBaixaPedido(Integer numPedido) throws JsonProcessingException {
        var json = converterJson.convertToJson(numPedido);
        rabbitTemplate.convertAndSend(queueRetiradaPedidoEntregueFila.getName(),json);
        log.info("--> Mensagem enviada para fila de baixa do pedido pós entregua...");
    }
}
