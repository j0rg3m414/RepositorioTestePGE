package com.restaurante.restaurante_system_client.application.ex;

/**
 * Exception de produtos
 * @author Jorge Maia
 * @since 09/11/2024
 */
public class ProdutoNaoEncontradoException extends RuntimeException{
    /**
     * Método que herda a exceptoin da RuntimeException
     * para busca de protudos
     * @author Jorge Maia
     * @since 09/11/2024
     */
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
