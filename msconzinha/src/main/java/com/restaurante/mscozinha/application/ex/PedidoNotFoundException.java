package com.restaurante.mscozinha.application.ex;

public class PedidoNotFoundException extends Exception{
    public PedidoNotFoundException() {
        super("Pedido não encontrado");
    }
}
