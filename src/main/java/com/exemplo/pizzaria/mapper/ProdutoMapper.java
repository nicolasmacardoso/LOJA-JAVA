package com.exemplo.pizzaria.mapper;

import com.exemplo.pizzaria.domain.entity.Produto;
import com.exemplo.pizzaria.dto.request.ProdutoRequestDTO;
import com.exemplo.pizzaria.dto.response.ProdutoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    
    ProdutoResponseDTO toResponse(Produto produto);
    
    Produto toEntity(ProdutoRequestDTO dto);
    
    void updateEntityFromDTO(ProdutoRequestDTO dto, @MappingTarget Produto produto);
}

