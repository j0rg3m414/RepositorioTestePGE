package com.restaurante.restaurante_system_client.pedido;

import com.restaurante.restaurante_system_client.application.PedidoService;
import com.restaurante.restaurante_system_client.application.PedidosResource;
import com.restaurante.restaurante_system_client.application.representation.PedidoSaveRequest;
import com.restaurante.restaurante_system_client.domain.*;
import com.restaurante.restaurante_system_client.domain.enumns.StatusPedido;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
public class PedidosResourceTest {

    private MockMvc mockMvc;
    @InjectMocks
    private PedidosResource pedidoResource;

    @Mock
    private PedidoService pedidoService;

    @Mock
    private Pedido pedidoEfetuado = new Pedido();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoResource).build();
    }

    @Test
    public void testListarPedidos() throws Exception {
        Produto produto1 = new Produto(1L,"Pizza",20.50);
        Produto produto2 = new Produto(2L, "Hamburguer", 15.0);

        ItemPedido itemPedido1 = new ItemPedido(1L,produto1,null,20.0,true);
        ItemPedido itemPedido2 = new ItemPedido(2L,produto2,null,40.0,true);

        Pedido pedido1 = new Pedido();
        pedido1.setId(1L); // Simulando um ID gerado
        pedido1.setItensPedido(Arrays.asList(itemPedido1,itemPedido2));
        pedido1.setStatus(StatusPedido.RECEBIDO);

        Pedido pedido2 = new Pedido();
        pedido2.setId(2L); // Simulando um ID gerado
        pedido2.setItensPedido(Arrays.asList(itemPedido1,itemPedido2));
        pedido2.setStatus(StatusPedido.RECEBIDO);

        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        when(pedidoService.getAllPedidos()).thenReturn(pedidos);

        mockMvc.perform(get("/pedidos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    public void testCriarPedido() throws Exception {

        Produto produto1 = new Produto(1L,"Pizza",20.50);
        Produto produto2 = new Produto(2L, "Hamburguer", 15.0);

        ItemPedido itemPedido1 = new ItemPedido(1L,produto1,null,20.0,true);
        ItemPedido itemPedido2 = new ItemPedido(2L,produto2,null,40.0,true);

        Pedido pedido = new Pedido();
        pedido.setId(1L); // Simulando um ID gerado
        pedido.setItensPedido(Arrays.asList(itemPedido1,itemPedido2));
        pedido.setStatus(StatusPedido.RECEBIDO);

        PedidoSaveRequest pedidoSaveRequest = new PedidoSaveRequest(Arrays.asList(1L,2L,3L),1L,2L);

        when(pedidoService.save(pedidoSaveRequest)).thenReturn(pedido);
        when(pedidoEfetuado.getItensPedido()).thenReturn(Arrays.asList(itemPedido1,itemPedido2));

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idProdutos\":[1,2,3],\"garcom\":\"1\",\"mesa\":1}"))
                .andExpect(status().isCreated());
    }
}
