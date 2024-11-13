package com.restaurante.restaurante_system_client.application.representation;

import com.restaurante.restaurante_system_client.domain.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO que será retornado para o ms-cozinha com as informaçoes
 * do pedido para que seja informado a produção de um produto
 * @author Jorge Maia
 * @since 06/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoGetRequest {
    private Integer numPedido;
    private List<ItemPedidoGetRequest> itensPedido;
    private String status;
}
