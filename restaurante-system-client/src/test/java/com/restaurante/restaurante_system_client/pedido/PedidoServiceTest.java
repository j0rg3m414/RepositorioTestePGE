package com.restaurante.restaurante_system_client.pedido;

import com.restaurante.restaurante_system_client.application.PedidoService;
import com.restaurante.restaurante_system_client.application.representation.PedidoSaveRequest;
import com.restaurante.restaurante_system_client.domain.*;
import com.restaurante.restaurante_system_client.domain.enumns.StatusPedido;
import com.restaurante.restaurante_system_client.repository.GarcomRepository;
import com.restaurante.restaurante_system_client.repository.MesaRepository;
import com.restaurante.restaurante_system_client.repository.PedidoRepository;
import com.restaurante.restaurante_system_client.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
public class PedidoServiceTest {
    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private GarcomRepository garcomRepository;

    @Mock
    private MesaRepository mesaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarPedido() {
        // Arrange
        Garcom garcom = new Garcom(1L,"Pato Donald",32165498791L);
        Mesa mesa = new Mesa(1L, 1, true);

        Produto produto1 = new Produto(1L,"Pizza",20.50);
        Produto produto2 = new Produto(2L, "Hamburguer", 15.0);

        ItemPedido itemPedido1 = new ItemPedido(1L,produto1,null,20.0,true);
        ItemPedido itemPedido2 = new ItemPedido(2L,produto2,null,40.0,true);

        Pedido novoPedido = new Pedido();

        PedidoSaveRequest pedidoSaveRequest = new PedidoSaveRequest();
        pedidoSaveRequest.setIdProdutos(Arrays.asList(1L,2L));
        pedidoSaveRequest.setGarcom(1L);
        pedidoSaveRequest.setMesa(1L);

        novoPedido.setStatus(StatusPedido.RECEBIDO);

        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> {
            Pedido pedido = invocation.getArgument(0);
            pedido.setId(1L); // Simulando um ID gerado
            pedido.setItensPedido(Arrays.asList(itemPedido1,itemPedido2));
            pedido.setStatus(StatusPedido.RECEBIDO);
            return pedido;
        });
        when(produtoRepository.findAllById(pedidoSaveRequest.getIdProdutos())).thenReturn(Arrays.asList(produto1, produto2));
        when(garcomRepository.findById(1L)).thenReturn(Optional.of(garcom));
        when(mesaRepository.findById(1L)).thenReturn(Optional.of(mesa));

        // Act
        Pedido pedidoCriado = pedidoService.save(pedidoSaveRequest);

        // Assert
        assertNotNull(pedidoCriado);
        assertEquals(1L, pedidoCriado.getId());
        assertEquals("RECEBIDO", pedidoCriado.getStatus().toString());
    }

    @Test
    public void testAlterarStatusPedido() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusPedido.PREPARANDO);
        when(pedidoRepository.findById(1L)).thenReturn(java.util.Optional.of(pedido));
        when(pedidoRepository.findBynumpedido(1)).thenReturn(pedido);

        // Act
        pedidoService.updateStatusPedido(1, "PRONTO");

        // Assert
        assertEquals("PRONTO", pedido.getStatus().toString());
    }
}
