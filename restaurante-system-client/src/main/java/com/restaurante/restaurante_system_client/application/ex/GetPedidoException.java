package com.restaurante.restaurante_system_client.application.ex;

/**
 * Exception de pedido
 * @author Jorge Maia
 * @since 07/11/2024
 */
public class GetPedidoException extends Exception{

    /**
     * Exception lançada ao tentar encontrar o pedido
     * Usada em PedidoService
     * @author Jorge Maia
     * @since 07/11/2024
     */
    public GetPedidoException(){
        super("Erro ao tentar encontrar o pedido solicitado.");
    }
}
