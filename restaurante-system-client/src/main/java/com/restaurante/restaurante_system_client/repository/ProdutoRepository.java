package com.restaurante.restaurante_system_client.repository;

import com.restaurante.restaurante_system_client.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository responsável por atender aos métodos relacionados ao banco
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
