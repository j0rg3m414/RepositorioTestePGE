package com.restaurante.mscozinha.application;

import com.restaurante.mscozinha.infra.mqueues.WebhookSubscriber;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
@AllArgsConstructor /*gera os construtores das classes injetadas*/
@CrossOrigin(origins = "http://localhost:3000") //Configura o CORS para o app react conseguir enxergar
public class WebhookController {

    private final PedidoService pedidoService;

    @PostMapping("/register")
    public ResponseEntity notificacaoWebhook(@RequestBody WebhookSubscriber webhook){
        pedidoService.registrarWebhook(webhook);
        return ResponseEntity.ok("Webhook registrado com sucesso");
    }
}
