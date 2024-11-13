package com.restaurante.restaurante_system_client.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.restaurante.restaurante_system_client.domain.enumns.StatusPedido;
import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
* Classe que representa a tabela de pedidos do restaurante
 * @author Jorge Maia
 * @since 01/11/2024
* */
@Entity
@Getter
@Setter
@NoArgsConstructor //Gera um construtor sem argumentos
@AllArgsConstructor //Gera um construtor com todos os argumentos
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable = false,updatable = false,nullable = false)
    private Integer numpedido;

    /**
     * Relacionamento com Item Pedido
     * Onde um pedido está relacionado com um ou mais item pedido
     */
    @OneToMany(mappedBy = "pedido",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ItemPedido> itensPedido = new ArrayList<>();

    /**
     * Relacionamento com Mesa
     * Onde um pedido está relacionado com um ou mais mesas
     */
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="idmesa", updatable = false)
    private Mesa mesa;

    /**
     * Relacionamento com Garçom
     * Onde um pedido está relacionado com um ou mais garçons
     */
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="idgarcom", updatable = false)
    private Garcom garcom;

    /**
     * Especifica que o enum deve ser amarmazenado como uma String
     */
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public Pedido(Garcom garcom, Mesa mesa) {
        this.garcom = garcom;
        this.mesa = mesa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(getId(), pedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
