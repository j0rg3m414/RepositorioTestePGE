package com.restaurante.restaurante_system_client.repository;

import com.restaurante.restaurante_system_client.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository responsável por atender aos métodos relacionados ao banco
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findBynumpedido(Integer numPedido);

    void deleteBynumpedido(Integer numPedido);
}
