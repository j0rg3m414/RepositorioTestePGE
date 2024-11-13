package com.restaurante.restaurante_system_client.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Classe que representa a tabela Gargom
 * @author Jorge Maia
 * @since 04/11/2024
 */
@Entity
@Data
@NoArgsConstructor //Gera um construtor sem argumentos
@AllArgsConstructor //Gera um construtor com todos os argumentos
@Table(name = "garcom")
public class Garcom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private Long cpf;

    /**
     * Relacionamento com Pedido
     * Onde um garcom pode atender a vários pedidos
     */
    @OneToMany(mappedBy = "garcom",cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public Garcom(long id, String nome, long cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }
}
