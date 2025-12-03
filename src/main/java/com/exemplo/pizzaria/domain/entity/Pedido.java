package com.exemplo.pizzaria.domain.entity;

import com.exemplo.pizzaria.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();
    
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private StatusPedido status = StatusPedido.PENDENTE;
    
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;
    
    @Column(name = "is_delivery", nullable = false)
    private Boolean isDelivery = true;
    
    @Column(name = "taxa_entrega", precision = 12, scale = 2)
    private BigDecimal taxaEntrega = BigDecimal.ZERO;
    
    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }
    
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        item.setPedido(this);
    }
    
    public void removerItem(ItemPedido item) {
        itens.remove(item);
        item.setPedido(null);
    }
}

