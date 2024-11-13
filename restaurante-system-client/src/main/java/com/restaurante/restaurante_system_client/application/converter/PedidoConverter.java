package com.restaurante.restaurante_system_client.application.converter;

import com.restaurante.restaurante_system_client.application.representation.ItemPedidoGetRequest;
import com.restaurante.restaurante_system_client.application.representation.PedidoGetRequest;
import com.restaurante.restaurante_system_client.domain.ItemPedido;
import com.restaurante.restaurante_system_client.domain.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe destinada a converters relacionado a pedido
 * @author Jorge Maia
 * @since 06/11/2024
 */
@Component
public class PedidoConverter {

    /**
     * Método para converter um pedido em dto
     * @author Jorge Maia
     * @since 06/11/2024
     */
    public static ResponseEntity<PedidoGetRequest> convertToDTO(Pedido pedido){

        PedidoGetRequest pedidoDTO = new PedidoGetRequest();
        pedidoDTO.setNumPedido(pedido.getNumpedido());
        pedidoDTO.setItensPedido(convertToDTOList(pedido.getItensPedido()));
        pedidoDTO.setStatus(pedido.getStatus().toString());

        return ResponseEntity.ok(pedidoDTO);
    }

    /**
     * Método para converter uma lista de pedidos em dto
     * @author Jorge Maia
     * @since 06/11/2024
     */
    public static List<PedidoGetRequest> convertToDTOListPedidos(List<Pedido> listaPedidos) {
        return listaPedidos.stream()
                .map(pgr -> new PedidoGetRequest(
                        pgr.getNumpedido(),
                        convertToDTOList(pgr.getItensPedido()),
                        pgr.getStatus().toString()))
                .collect(Collectors.toList());
    }

    /**
     * Método para converter uma lista de itens pedidos em dto
     * @author Jorge Maia
     * @since 06/11/2024
     */
    public static List<ItemPedidoGetRequest> convertToDTOList(List<ItemPedido> itensPedidoList) {
        return itensPedidoList.stream()
                .map(itensPedido
                        -> new ItemPedidoGetRequest(
                                itensPedido.getId(),
                        itensPedido.getPedido().getId(),
                        itensPedido.getStatus()))
                .collect(Collectors.toList());
    }
}


