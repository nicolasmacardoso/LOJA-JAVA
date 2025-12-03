package com.exemplo.pizzaria.mapper;

import com.exemplo.pizzaria.domain.entity.Entregador;
import com.exemplo.pizzaria.dto.request.EntregadorRequestDTO;
import com.exemplo.pizzaria.dto.response.EntregadorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntregadorMapper {
    
    EntregadorResponseDTO toResponse(Entregador entregador);
    
    Entregador toEntity(EntregadorRequestDTO dto);
    
    void updateEntityFromDTO(EntregadorRequestDTO dto, @MappingTarget Entregador entregador);
}

