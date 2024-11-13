package com.restaurante.mscozinha.application;

import com.restaurante.mscozinha.application.ex.ErroComunicacaoMicroservicesException;
import com.restaurante.mscozinha.application.ex.PedidoNotFoundException;
import com.restaurante.mscozinha.domain.model.PedidoGetRequest;
import com.restaurante.mscozinha.domain.model.ProdutoEmPreparo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cozinha")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CozinhaController {

    private final PedidoService pedidoService;

    /**
     * Teste
     * @return
     */
    @GetMapping
    public String status(){
        return "Respondendo com sucesso";
    }

    /**
     * Endpoint para informar mudnça de status do pedido
     * passando o número do pedido e o novostatus
     * @param numPedido
     * @return String
     */
    @PostMapping(value = "informar-preparo", params = "numpedido")
    public ResponseEntity informarProdutoEmPreparo(@RequestParam("numpedido") Integer numPedido) throws Exception {
        try {
            pedidoService.informarMudancaStatus(numPedido,"PREPARANDO");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok("PEDIDO " + numPedido + " ESTÁ SENDO PREPARADO!");
    }

    /**
     * Notificação prato pronto
     * @param numPedido
     * @return
     */
    @PostMapping(value = "notificacao-prato-pronto", params = "numpedido")
    public ResponseEntity notificaPratoPronto(@RequestParam("numpedido") Integer numPedido) throws Exception {
        try {
            pedidoService.informarMudancaStatus(numPedido,"PRONTO");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok("PEDIDO " + numPedido + " ESTÁ PRONTO!");
    }

    /**
     * Usado pelo Garçom na interface para notificar prato entregue
     * @param numPedido
     * @return
     */
    @PostMapping(value = "notificacao-prato-entregue", params = "numpedido")
    public ResponseEntity notificaPratoEntregue(@RequestParam("numpedido") Integer numPedido) throws Exception {
        try {
            pedidoService.informarMudancaStatus(numPedido,"ENTREGUE");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok("PEDIDO " + numPedido + " ESTÁ ENTREGUE!");
    }
}
