package com.restaurante.restaurante_system_client.application.representation;

import com.restaurante.restaurante_system_client.domain.ItemPedido;
import com.restaurante.restaurante_system_client.domain.Pedido;
import com.restaurante.restaurante_system_client.domain.Produto;
import com.restaurante.restaurante_system_client.domain.enumns.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * DTO que representa os dados do pedido
 * @author Jorge Mdaia
 * @since 01/11/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoSaveRequest {

    private List<Long> idProdutos;
    private Long garcom;
    private Long mesa;

}
