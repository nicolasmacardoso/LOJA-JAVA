package com.exemplo.pizzaria.mapper;

import com.exemplo.pizzaria.domain.entity.ItemPedido;
import com.exemplo.pizzaria.dto.response.ItemPedidoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProdutoMapper.class)
public interface ItemPedidoMapper {
    
    @Mapping(source = "produto", target = "produto")
    ItemPedidoResponseDTO toResponse(ItemPedido itemPedido);
}

