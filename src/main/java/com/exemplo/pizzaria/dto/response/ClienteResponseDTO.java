package com.exemplo.pizzaria.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {
    
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private LocalDateTime dataCadastro;
}

