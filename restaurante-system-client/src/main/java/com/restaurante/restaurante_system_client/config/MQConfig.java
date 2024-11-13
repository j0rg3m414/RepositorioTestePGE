package com.restaurante.restaurante_system_client.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para envio de mensagens (pedidos) para fila
 * no RabbitMQ
 * @author Jorge Maia
 * @since 07/11/2024
 */
@Configuration
public class MQConfig {

    /**
     * Fila de novo pedido
     */
    @Value("${mq.queues.emissao-pedido}")
    private String emissaoPedidoFila;

    /**
     * Notificação de novo pedido;
     * @return
     */
    @Bean
    public Queue queueEmissaoPedido(){
        return new Queue(emissaoPedidoFila, true);
    }

    /**
     * Fila de mudança de status do pedido
     */
    @Value("${mq.queues.emissao-status-pedido}")
    private String emissaoMudancaStatusPedido;

    /**
     * Emissão status pedido
     * @return
     */
    @Bean
    public Queue queueEmissaoMudancaStatus(){
        return new Queue(emissaoMudancaStatusPedido, true);
    }
}
