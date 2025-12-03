package com.exemplo.pizzaria.domain.repository;

import com.exemplo.pizzaria.domain.entity.Pedido;
import com.exemplo.pizzaria.domain.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    List<Pedido> findByStatus(StatusPedido status);
    
    List<Pedido> findByClienteId(Long clienteId);
}

