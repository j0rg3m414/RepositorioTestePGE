package com.restaurante.restaurante_system_client.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restaurante.restaurante_system_client.application.converter.ConverterToJson;
import com.restaurante.restaurante_system_client.application.representation.PedidoGetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Publisher (produto) de informação para filas no RabbitMQ
 * Fila de pedidos
 * @author Jorge Maia
 * @since  07/11/2024
 */
@Component
@RequiredArgsConstructor
public class PublisherFilaPedidos {

    /**
     * Injeções de dependencias
     */
    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoPedido;
    private final ConverterToJson converterJson;

    /**
     * Método responsável por enviar pedidos para fila
     * @Param PedidoGetRequest
     */
    public void enviarPedidoFila(PedidoGetRequest dadosFila) throws JsonProcessingException {
        var json = converterJson.convertToJson(dadosFila);
        rabbitTemplate.convertAndSend(queueEmissaoPedido.getName(),json);
    }

    /**
     * Método responsável por enviar uma lista de pedidos para fila
     * @Param List<PedidoGetRequest>
     */
    public void enviarListaPedidosFila(List<PedidoGetRequest> listaPedidos) {
        listaPedidos.forEach(lp -> {
                    try {
                        var json = converterJson.convertToJson(lp);
                        rabbitTemplate.convertAndSend(queueEmissaoPedido.getName(),json);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
