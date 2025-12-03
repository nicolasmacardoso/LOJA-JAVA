package com.exemplo.pizzaria.domain.service;

import com.exemplo.pizzaria.domain.entity.ItemPedido;
import com.exemplo.pizzaria.domain.entity.Pedido;
import com.exemplo.pizzaria.domain.entity.Produto;
import com.exemplo.pizzaria.domain.enums.StatusPedido;
import com.exemplo.pizzaria.domain.exception.BadRequestException;
import com.exemplo.pizzaria.domain.exception.EntityNotFoundException;
import com.exemplo.pizzaria.domain.repository.PedidoRepository;
import com.exemplo.pizzaria.dto.request.ItemPedidoRequestDTO;
import com.exemplo.pizzaria.dto.request.PedidoCreateRequestDTO;
import com.exemplo.pizzaria.dto.request.PedidoUpdateRequestDTO;
import com.exemplo.pizzaria.dto.response.PedidoResponseDTO;
import com.exemplo.pizzaria.mapper.ItemPedidoMapper;
import com.exemplo.pizzaria.mapper.PedidoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final EntregadorService entregadorService;
    private final PedidoMapper pedidoMapper;
    private final ItemPedidoMapper itemPedidoMapper;
    
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.findAll().stream()
                .map(pedidoMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido", id));
        return pedidoMapper.toResponse(pedido);
    }
    
    @Transactional
    public PedidoResponseDTO create(PedidoCreateRequestDTO dto) {
        // Buscar cliente
        var cliente = clienteService.findEntityById(dto.getClienteId());
        
        // Criar pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setIsDelivery(dto.getIsDelivery());
        pedido.setTaxaEntrega(dto.getTaxaEntrega() != null ? dto.getTaxaEntrega() : BigDecimal.ZERO);
        pedido.setStatus(StatusPedido.PENDENTE);
        
        // Processar itens
        BigDecimal totalItens = BigDecimal.ZERO;
        
        for (ItemPedidoRequestDTO itemDTO : dto.getItens()) {
            // Buscar produto
            Produto produto = produtoService.findEntityById(itemDTO.getProdutoId());
            
            // Validar disponibilidade
            if (!produto.getDisponivel()) {
                throw new BadRequestException(
                    String.format("Produto '%s' não está disponível", produto.getNome())
                );
            }
            
            // Criar item do pedido
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.calcularSubtotal(); // Calcula subtotal
            
            pedido.adicionarItem(item);
            totalItens = totalItens.add(item.getSubtotal());
        }
        
        // Calcular total do pedido
        BigDecimal total = totalItens.add(pedido.getTaxaEntrega());
        pedido.setTotal(total);
        
        // Salvar pedido (cascade salva os itens)
        pedido = pedidoRepository.save(pedido);
        
        return pedidoMapper.toResponse(pedido);
    }
    
    @Transactional
    public PedidoResponseDTO update(Long id, PedidoUpdateRequestDTO dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido", id));
        
        // Validar transição de status
        validarTransicaoStatus(pedido.getStatus(), dto.getStatus());
        
        // Atualizar status
        pedido.setStatus(dto.getStatus());
        
        // Atribuir entregador se fornecido e for delivery
        if (dto.getEntregadorId() != null && pedido.getIsDelivery()) {
            var entregador = entregadorService.findEntityById(dto.getEntregadorId());
            if (!entregador.getDisponivel()) {
                throw new BadRequestException("Entregador não está disponível");
            }
            pedido.setEntregador(entregador);
        }
        
        pedido = pedidoRepository.save(pedido);
        return pedidoMapper.toResponse(pedido);
    }
    
    @Transactional
    public PedidoResponseDTO updateStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido", id));
        
        validarTransicaoStatus(pedido.getStatus(), novoStatus);
        pedido.setStatus(novoStatus);
        
        pedido = pedidoRepository.save(pedido);
        return pedidoMapper.toResponse(pedido);
    }
    
    @Transactional
    public void delete(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido", id));
        
        // Regra: só pode deletar pedidos cancelados
        if (pedido.getStatus() != StatusPedido.CANCELADO) {
            throw new BadRequestException(
                String.format("Não é possível deletar pedido com status '%s'. Apenas pedidos cancelados podem ser deletados.", 
                    pedido.getStatus())
            );
        }
        
        pedidoRepository.delete(pedido);
    }
    
    private void validarTransicaoStatus(StatusPedido statusAtual, StatusPedido novoStatus) {
        // Não pode alterar status de pedido entregue
        if (statusAtual == StatusPedido.ENTREGUE) {
            throw new BadRequestException("Não é possível alterar status de pedido já entregue");
        }
        
        // Não pode alterar status de pedido cancelado
        if (statusAtual == StatusPedido.CANCELADO) {
            throw new BadRequestException("Não é possível alterar status de pedido cancelado");
        }
        
        // Validações específicas de transição
        if (novoStatus == StatusPedido.CANCELADO) {
            // Cancelamento permitido em qualquer status exceto ENTREGUE
            return;
        }
        
        // Fluxo normal: PENDENTE -> PREPARANDO -> SAIU_ENTREGA -> ENTREGUE
        // Permitir qualquer transição válida (flexível para o projeto)
    }
}

