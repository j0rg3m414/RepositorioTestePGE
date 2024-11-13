package com.restaurante.mscozinha.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para envio de menssagem de
 * mudança de status do pedido para ms-client
 * @author Jorge Maia
 * @since 07/11/2024
 */
@Configuration
public class MQConfig {

    /**
     * Usado para mudança de status dos pedidos
     */
    @Value("${mq.queues.emissao-status-pedido}")
    private String emissaoMudancaStatusPedido;

    @Bean
    public Queue queueEmissaoMudancaStatus(){
        return new Queue(emissaoMudancaStatusPedido, true);
    }

    /**
     * Usado para retirada de pedido pronto da fila
     */
    @Value("${mq.queues.retirada-pedido-entregue-fila}")
    private String queueRetiradaPedidoEntregueFila;

    @Bean
    public Queue queueRetiradaPedidoEntregueFila(){
        return new Queue(queueRetiradaPedidoEntregueFila, true);
    }
}
