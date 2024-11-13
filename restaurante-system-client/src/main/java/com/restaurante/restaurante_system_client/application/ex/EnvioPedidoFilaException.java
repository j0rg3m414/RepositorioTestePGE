package com.restaurante.restaurante_system_client.application.ex;

/**
 * Exception para o entvio de pedidos
 * @author Jorge Maia
 * @since 07/11/2024
 */
public class EnvioPedidoFilaException extends RuntimeException{

    /**
     * Método que herda a exceptoin da RuntimeException
     * @author Jorge Maia
     * @since 07/11/2024
     */
    public EnvioPedidoFilaException(String message) {
        super(message);
    }
}
