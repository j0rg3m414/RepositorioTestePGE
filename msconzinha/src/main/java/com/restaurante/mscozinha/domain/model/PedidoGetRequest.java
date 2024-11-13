package com.restaurante.mscozinha.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoGetRequest {
    private Integer numPedido;
    private List<ItemPedido> itensPedido;
    private String status;
}
