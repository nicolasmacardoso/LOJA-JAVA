package com.exemplo.pizzaria.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entregador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entregador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 150)
    private String nome;
    
    @Column(length = 20)
    private String telefone;
    
    @Column(nullable = false)
    private Boolean disponivel = true;
    
    @OneToMany(mappedBy = "entregador")
    private List<Pedido> pedidos = new ArrayList<>();
}

