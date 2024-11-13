package com.restaurante.restaurante_system_client.application.representation;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO que representa a gravação de um produto
 * @author Jorge Maia
 * @since 06/11/2024
 */
@Data
@AllArgsConstructor
public class ItemPedidoGetRequest {

    private Long id;
    private Long idPedido;
    private Boolean status;
}
