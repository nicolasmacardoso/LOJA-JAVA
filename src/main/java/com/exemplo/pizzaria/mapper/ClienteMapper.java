package com.exemplo.pizzaria.mapper;

import com.exemplo.pizzaria.domain.entity.Cliente;
import com.exemplo.pizzaria.dto.request.ClienteRequestDTO;
import com.exemplo.pizzaria.dto.response.ClienteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    
    ClienteResponseDTO toResponse(Cliente cliente);
    
    Cliente toEntity(ClienteRequestDTO dto);
    
    void updateEntityFromDTO(ClienteRequestDTO dto, @MappingTarget Cliente cliente);
}

