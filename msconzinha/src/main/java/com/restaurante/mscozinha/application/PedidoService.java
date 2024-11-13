package com.restaurante.mscozinha.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restaurante.mscozinha.application.ex.EnvioPedidoFilaException;
import com.restaurante.mscozinha.application.ex.ErroComunicacaoMicroservicesException;
import com.restaurante.mscozinha.application.ex.PedidoNotFoundException;
import com.restaurante.mscozinha.domain.model.PedidoGetRequest;
import com.restaurante.mscozinha.infra.mqueues.PublisherInformaMudancaStatus;
import com.restaurante.mscozinha.infra.mqueues.WebhookSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PublisherInformaMudancaStatus publisherInformaMudancaStatus;
    private final List<WebhookSubscriber> webhooks = new CopyOnWriteArrayList<>();

    /**
     * Envia mensagem para fila de mudança de status do pedido no RabbitMQ
     * @param numPedido
     * @return
     */
    public PedidoGetRequest informarMudancaStatus(Integer numPedido, String novoStatus) throws Exception {
        try {

            PedidoGetRequest pedidoGetRequest = new PedidoGetRequest();
            pedidoGetRequest.setNumPedido(numPedido);
            pedidoGetRequest.setStatus(novoStatus);
            publisherInformaMudancaStatus.enviarNovoStatusPedido(pedidoGetRequest);
            //Notificar webhooks
            notificarWebhooks(pedidoGetRequest);
            //Verifica se o status é ENTREGUE para solicitar a retirada da fila
            if(novoStatus == "ENTREGUE")
                publisherInformaMudancaStatus.enviarSolicitacaoBaixaPedido(pedidoGetRequest.getNumPedido());
            return pedidoGetRequest;
        }catch(JsonProcessingException e) {
            throw new Exception("Erro ao tentar processar JSON" + e.getMessage());
        }
    }

    public void registrarWebhook(WebhookSubscriber webhook){
        webhooks.add(webhook);
    }

    private void notificarWebhooks(PedidoGetRequest pedidoGetRequest){
        webhooks.forEach(webhook ->{
                try {
                    webhook.notificar(pedidoGetRequest.getStatus());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Erro ao notificar webhook: " + e.getMessage());
                }
        });
    }
}
