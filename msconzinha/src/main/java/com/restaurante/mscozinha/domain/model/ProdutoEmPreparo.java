package com.restaurante.mscozinha.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class ProdutoEmPreparo {
    private Integer numPedido;
    private List<ItemPedido> itensPedido;
    private Boolean status;
}
