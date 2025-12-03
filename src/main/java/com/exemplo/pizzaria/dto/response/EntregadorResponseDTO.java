package com.exemplo.pizzaria.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorResponseDTO {
    
    private Long id;
    private String nome;
    private String telefone;
    private Boolean disponivel;
}

