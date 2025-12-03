package com.exemplo.pizzaria.domain.entity;

import com.exemplo.pizzaria.domain.enums.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 150)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Categoria categoria;
    
    @Column(nullable = false)
    private Boolean disponivel = true;
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido = new ArrayList<>();
}

