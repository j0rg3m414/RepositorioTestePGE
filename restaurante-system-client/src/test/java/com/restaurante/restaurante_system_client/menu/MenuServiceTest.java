package com.restaurante.restaurante_system_client.menu;

import com.restaurante.restaurante_system_client.application.MenuService;
import com.restaurante.restaurante_system_client.domain.Produto;
import com.restaurante.restaurante_system_client.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@RequiredArgsConstructor
public class MenuServiceTest {
    @InjectMocks
    private MenuService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testListarProdutos() {
        // Arrange
        Produto produto1 = new Produto(1L,"Pizza",20.50);
        Produto produto2 = new Produto(2L, "Hamburguer", 15.0);
        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto1, produto2));

        // Act
        List<Produto> produtos = produtoService.getAllProdutos();

        // Assert
        assertNotNull(produtos);
        assertEquals(2, produtos.size());
        assertEquals("Pizza", produtos.get(0).getDescricao());
        assertEquals("Hamburguer", produtos.get(1).getDescricao());
    }
}
