package com.restaurante.restaurante_system_client.domain.enumns;

import lombok.Getter;

/**
 * Enum para status do pedido;
 */
public enum StatusPedido {

    RECEBIDO("Pedido recebido"),
    PREPARANDO("Pedido em preparação"),
    PRONTO("Pedido pronto"),
    INDEFINIDO("Indefinido"), //usado no default de um switch case
    ENTREGUE("Pedido entregue");

    @Getter
    private final String descricao;

    StatusPedido(String descricao){
        this.descricao = descricao;
    }
}
