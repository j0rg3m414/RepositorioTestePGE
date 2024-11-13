package com.restaurante.restaurante_system_client.application;

import com.restaurante.restaurante_system_client.application.representation.PedidoSaveRequest;
import com.restaurante.restaurante_system_client.domain.Pedido;
import com.restaurante.restaurante_system_client.domain.Produto;
import com.restaurante.restaurante_system_client.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe responsável pela lógica do negócio
 * O Lombok irá criar o construtor em tempo de compilação
 * @author Jorge Mdaia
 * @since 31/10/2024
 */
@Service
@RequiredArgsConstructor
public class MenuService {

    /**
     * Injeção de dependicia de MenuSerive
     */
    private final ProdutoRepository repository;

    /**
     * Método que lista os todos os produtos do menu
     *
     * @return produtos
     */
    @Transactional
    public List<Produto> getAllProdutos(){
        return repository.findAll();
    }
}
