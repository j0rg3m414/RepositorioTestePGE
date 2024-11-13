package com.restaurante.restaurante_system_client.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe que representa a tabela de Produtos do restaurante no banco
 * @author Jorge Maia
 * @since 01/11/2024
 * */
@Entity
@Getter
@Setter
@NoArgsConstructor //Gera um construtor sem argumentos
@AllArgsConstructor //Gera um construtor com todos os argumentos
@Table(name="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Column
    private Double preco;

    /**
     * Relacionamento com Item Pedido
     * Onde um produto pode está relacionado com um ou mais Item pedido
     */
    @OneToMany(mappedBy = "produto",cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ItemPedido> itensPedido  = new HashSet<>();

    public Produto(Long id, String descricao, Double preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(getId(), produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
