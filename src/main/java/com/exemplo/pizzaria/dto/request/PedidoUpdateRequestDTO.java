package com.exemplo.pizzaria.dto.request;

import com.exemplo.pizzaria.domain.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoUpdateRequestDTO {
    
    private Long entregadorId;
    
    @NotNull(message = "Status é obrigatório")
    private StatusPedido status;
}

