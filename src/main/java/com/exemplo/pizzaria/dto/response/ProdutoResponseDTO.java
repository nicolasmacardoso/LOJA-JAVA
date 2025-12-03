package com.exemplo.pizzaria.dto.response;

import com.exemplo.pizzaria.domain.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {
    
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;
    private Boolean disponivel;
}

