package com.restaurante.restaurante_system_client.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
* Classe que representa a tabela de Mesa
 * @author Jorge Maia
 * @since 01/11/2024
* */
@Entity
@Data
@NoArgsConstructor //Gera um construtor sem argumentos
@AllArgsConstructor //Gera um construtor com todos os argumentos
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer numMesa;

    /**
     * Relacionamento com Pedido
     * Onde uma ou mais mesa está relacionado com um ou vários pedidos
     */
    @OneToMany(mappedBy = "mesa",cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Pedido> pedidos  = new HashSet<>();

    @Column //ABERTA = 0 false, FECHADA = 1 true
    private Boolean status;

    public Mesa(Long id, Integer numMesa, Boolean status) {
        this.id = id;
        this.numMesa = numMesa;
        this.status = status;
    }
}
