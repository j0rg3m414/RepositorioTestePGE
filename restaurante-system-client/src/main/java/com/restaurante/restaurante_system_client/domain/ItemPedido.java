package com.restaurante.restaurante_system_client.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * Classe que representa a tabela de ItemPedido
 * @author Jorge Maia
 * @since 05/11/2024
 */
@Entity
@Getter
@Setter
@NoArgsConstructor //Gera um construtor sem argumentos
@AllArgsConstructor //Gera um construtor com todos os argumentos
@Table(name = "ItemPedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relacionamento com Produto
     * Onde um ou mais item pedido está relacionado com um produto
     */
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="idproduto", updatable = false)
    private Produto produto;

    /**
     * Relacionamento com Pedido
     * Onde um ou mais item pedido está relacionado com um pedido
     */
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="idpedido", updatable = false)
    private Pedido pedido;

    @Column
    private Double preco;

    @Column //false = pronto, true = preparando
    private Boolean status;

    public ItemPedido(Produto produto, Pedido pedido, Double preco, Boolean status) {
        this.produto = produto;
        this.pedido = pedido;
        this.preco = preco;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        if (id == null) return (int) Math.random();
        return Objects.hash(getId());
    }
}
