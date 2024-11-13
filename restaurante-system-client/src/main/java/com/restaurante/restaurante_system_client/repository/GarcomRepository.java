package com.restaurante.restaurante_system_client.repository;

import com.restaurante.restaurante_system_client.domain.Garcom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository responsável por atender aos métodos relacionados ao banco
 */
public interface GarcomRepository extends JpaRepository<Garcom, Long>{
}
