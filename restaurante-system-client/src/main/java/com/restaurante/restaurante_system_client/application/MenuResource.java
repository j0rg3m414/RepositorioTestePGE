package com.restaurante.restaurante_system_client.application;


import com.restaurante.restaurante_system_client.domain.Produto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para os recursos do cardápio
 * @author Jorge Mdaia
 * @since 31/10/2024
 */
@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuResource {

    /**
     * Injeção de dependicia de MenuSerive
     */
    private final MenuService service;

    /**
     * Método que lista os todos os produtos disponíveis
     * @return pedidos
     */
    @GetMapping
    public ResponseEntity<List<Produto>> getMenu(){
        var produtos = service.getAllProdutos();
        if(produtos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtos);
    }
}
