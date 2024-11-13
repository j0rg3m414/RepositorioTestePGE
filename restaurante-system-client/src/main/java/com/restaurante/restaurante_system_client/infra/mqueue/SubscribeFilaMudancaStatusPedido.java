package com.restaurante.restaurante_system_client.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.restaurante_system_client.application.PedidoService;
import com.restaurante.restaurante_system_client.application.representation.PedidoGetRequest;
import com.restaurante.restaurante_system_client.domain.Pedido;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Subscriber responsável por escutar a fila
 * de mudança de status do pedido
 * @autor Jorge Maia
 * @since 08/11/2024
 */
@Component
@AllArgsConstructor
public class SubscribeFilaMudancaStatusPedido {

    /**
     * Injeção de dependência
     */
    private final PedidoService servicePedido;

    /**
     * Listener do RabbitMQ responsável pela fila de status pedido
     */
    @RabbitListener(queues = "${mq.queues.emissao-status-pedido}")
    public void receberMudancaStatusPedido(@Payload String payload){
        //payload será o json recebido do tipo PedidoGetRequest
        try{
            var mapper = new ObjectMapper();

            PedidoGetRequest pedidoGetRequest = mapper.readValue(payload, PedidoGetRequest.class);

            servicePedido.updateStatusPedido(
                    pedidoGetRequest.getNumPedido(),
                    pedidoGetRequest.getStatus());

        } catch (JsonProcessingException e){
            throw new RuntimeException("Erro ao processar JSON", e);
        }
    }

    /**
     * Listener do RabbitMQ responsável pela retirada de pedido pronto da fila vindo da cozinha
     */
    @RabbitListener(queues = "${mq.queues.retirada-pedido-entregue-fila}")
    public void receberSolicitacaoRetiradaPedidoEntregueFila(@Payload String payload){
        servicePedido.deletePedido(Long.parseLong(payload));
    }
}
