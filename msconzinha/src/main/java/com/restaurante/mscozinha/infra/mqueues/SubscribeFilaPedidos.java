package com.restaurante.mscozinha.infra.mqueues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.mscozinha.domain.model.PedidoGetRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SubscribeFilaPedidos {

    /**
     * Listener para mudança de status de pedido
     * @param payload
     * @throws JsonProcessingException
     */
    @RabbitListener(queues = "${mq.queues.emissao-status-pedido}")
    public void receberMudancaStatusPedido(@Payload String payload) throws JsonProcessingException {
        //payload será o json recebido do tipo PedidoGetRequest
        try{
            var mapper = new ObjectMapper();
            PedidoGetRequest pedidoGetRequest = mapper.readValue(payload, PedidoGetRequest.class);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Para fila de pedidos
     * @param payload
     * @throws JsonProcessingException
     */
    @RabbitListener(queues = "${mq.queues.emissao-pedido}")
    public void receberPedidos(@Payload String payload) throws JsonProcessingException {
        //payload será o json recebido do tipo PedidoGetRequest
        StringBuilder sb = new StringBuilder();
        try{
            var mapper = new ObjectMapper();
            PedidoGetRequest pedidoGetRequest = mapper.readValue(payload, PedidoGetRequest.class);

            sb.append("ATENÇÃO novo pedido recebido!\n");
            sb.append("Número do pedido: " + pedidoGetRequest.getNumPedido() + "\n");
            sb.append("Itens: \n");
            pedidoGetRequest.getItensPedido().forEach(item ->{
                        sb.append(item.getId() + "| Status: ");
                        if(item.isStatus()){
                            sb.append("Preparando\n");
                        } else {
                            sb.append("Pronto\n");
                        }
                    });
            sb.append("Status Pedido: " + pedidoGetRequest.getStatus() + "\n");

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println(sb);
        }
    }
}
