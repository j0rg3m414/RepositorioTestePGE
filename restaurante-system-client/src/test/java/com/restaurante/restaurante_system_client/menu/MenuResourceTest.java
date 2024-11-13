package com.restaurante.restaurante_system_client.menu;

import com.restaurante.restaurante_system_client.application.MenuResource;
import com.restaurante.restaurante_system_client.application.MenuService;
import com.restaurante.restaurante_system_client.domain.Produto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
public class MenuResourceTest {
    private MockMvc mockMvc;

    @Mock
    private MenuService menuService;

    @InjectMocks
    private MenuResource menuResource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(menuResource).build();
    }

    @Test
    public void testListarProdutos() throws Exception {
        Produto produto1 = new Produto(1L, "Pizza", 20.0);
        Produto produto2 = new Produto(2L, "Hamburguer", 15.0);
        List<Produto> produtos = Arrays.asList(produto1, produto2);

        when(menuService.getAllProdutos()).thenReturn(produtos);

        mockMvc.perform(get("/menu")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descricao").value("Pizza"))
                .andExpect(jsonPath("$[1].descricao").value("Hamburguer"));
    }
}
