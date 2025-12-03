package com.exemplo.pizzaria.mapper;

import com.exemplo.pizzaria.domain.entity.Pedido;
import com.exemplo.pizzaria.dto.response.PedidoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, EntregadorMapper.class, ItemPedidoMapper.class})
public interface PedidoMapper {
    
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "entregador", target = "entregador")
    @Mapping(source = "itens", target = "itens")
    PedidoResponseDTO toResponse(Pedido pedido);
}

