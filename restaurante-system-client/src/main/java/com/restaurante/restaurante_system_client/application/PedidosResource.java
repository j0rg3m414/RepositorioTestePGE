package com.restaurante.restaurante_system_client.application;

import com.restaurante.restaurante_system_client.application.ex.EnvioPedidoFilaException;
import com.restaurante.restaurante_system_client.application.ex.GetPedidoException;
import com.restaurante.restaurante_system_client.application.representation.PedidoSaveRequest;
import com.restaurante.restaurante_system_client.domain.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para os recursos relacionados ao pedido
 * @author Jorge Mdaia
 * @since 31/10/2024
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidosResource {

    private final PedidoService service;

    /**
     * Listar todos os pedidos
     * @return ResponseEntity<List<Pedido>>
     */
    @GetMapping
    public ResponseEntity<List<Pedido>> listaPedidos(){
        List<Pedido> pedidos = service.getAllPedidos();
        service.enviarListaPedidoParaFila(pedidos);
        if(pedidos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Gravar novo pedido
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity save(@RequestBody PedidoSaveRequest request){

        var pedidoEfetuado = service.save(request);
        try {
            service.enviarPedidoParaFila(pedidoEfetuado);
        } catch (EnvioPedidoFilaException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoEfetuado);
    }

    /**
     * Buscar um pedido a partir de um número de pedido
     * @param numPedido
     * @return ResponseEntity<String>
     */
    @GetMapping(value="obterPedido", params="numPedido")
    public ResponseEntity<String> obterPedidoByNumPedido(@RequestParam("numPedido") Integer numPedido){
        try {
            var pedidoRetornado = service.getPedidoByNumPedido(numPedido);
            return ResponseEntity.ok(
                    "Pedido: " + pedidoRetornado.getBody().getNumPedido() + "/ Status: " + pedidoRetornado.getBody().getStatus());
        } catch (GetPedidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pedido não encontrado!");
        }
    }
}
