package com.exemplo.pizzaria.dto.response;

import com.exemplo.pizzaria.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {
    
    private Long id;
    private ClienteResponseDTO cliente;
    private EntregadorResponseDTO entregador;
    private List<ItemPedidoResponseDTO> itens = new ArrayList<>();
    private LocalDateTime dataCriacao;
    private StatusPedido status;
    private BigDecimal total;
    private Boolean isDelivery;
    private BigDecimal taxaEntrega;
}

