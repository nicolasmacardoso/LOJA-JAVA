package com.exemplo.pizzaria.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 150)
    private String nome;
    
    @Column(unique = true, length = 14)
    private String cpf;
    
    @Column(length = 20)
    private String telefone;
    
    @Column(length = 150)
    private String email;
    
    @Column(length = 255)
    private String endereco;
    
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
    }
}

