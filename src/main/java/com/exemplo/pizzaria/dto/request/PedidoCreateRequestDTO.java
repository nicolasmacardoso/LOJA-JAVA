package com.exemplo.pizzaria.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCreateRequestDTO {
    
    @NotNull(message = "ID do cliente é obrigatório")
    private Long clienteId;
    
    @NotNull(message = "isDelivery é obrigatório")
    private Boolean isDelivery;
    
    @DecimalMin(value = "0.0", message = "Taxa de entrega não pode ser negativa")
    private BigDecimal taxaEntrega = BigDecimal.ZERO;
    
    @NotNull(message = "Lista de itens é obrigatória")
    @Size(min = 1, message = "Pedido deve ter pelo menos um item")
    @Valid
    private List<ItemPedidoRequestDTO> itens = new ArrayList<>();
}

