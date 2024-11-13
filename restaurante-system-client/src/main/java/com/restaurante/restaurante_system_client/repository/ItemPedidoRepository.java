package com.restaurante.restaurante_system_client.repository;

import com.restaurante.restaurante_system_client.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
