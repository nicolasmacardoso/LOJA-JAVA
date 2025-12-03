package com.exemplo.pizzaria.domain.service;

import com.exemplo.pizzaria.domain.entity.Produto;
import com.exemplo.pizzaria.domain.exception.BadRequestException;
import com.exemplo.pizzaria.domain.exception.EntityNotFoundException;
import com.exemplo.pizzaria.domain.repository.ProdutoRepository;
import com.exemplo.pizzaria.dto.request.ProdutoRequestDTO;
import com.exemplo.pizzaria.dto.response.ProdutoResponseDTO;
import com.exemplo.pizzaria.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll().stream()
                .map(produtoMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public ProdutoResponseDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto", id));
        return produtoMapper.toResponse(produto);
    }
    
    @Transactional
    public ProdutoResponseDTO create(ProdutoRequestDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        produto = produtoRepository.save(produto);
        return produtoMapper.toResponse(produto);
    }
    
    @Transactional
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto", id));
        
        produtoMapper.updateEntityFromDTO(dto, produto);
        produto = produtoRepository.save(produto);
        return produtoMapper.toResponse(produto);
    }
    
    @Transactional
    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto", id));

        // VALIDAÇÃO: Impedir deleção se tiver itens de pedido
        if (produto.getItensPedido() != null && !produto.getItensPedido().isEmpty()) {
            throw new BadRequestException(
                    String.format("Não é possível deletar o produto '%s'. Existem pedidos associados a este produto.",
                            produto.getNome())
            );
        }
        produtoRepository.delete(produto);
    }
    
    @Transactional(readOnly = true)
    public Produto findEntityById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto", id));
    }
    
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> findDisponiveis() {
        return produtoRepository.findByDisponivelTrue().stream()
                .map(produtoMapper::toResponse)
                .collect(Collectors.toList());
    }
}

