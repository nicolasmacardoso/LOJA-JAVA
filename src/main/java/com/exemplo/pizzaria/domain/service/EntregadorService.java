package com.exemplo.pizzaria.domain.service;

import com.exemplo.pizzaria.domain.entity.Entregador;
import com.exemplo.pizzaria.domain.exception.EntityNotFoundException;
import com.exemplo.pizzaria.domain.repository.EntregadorRepository;
import com.exemplo.pizzaria.dto.request.EntregadorRequestDTO;
import com.exemplo.pizzaria.dto.response.EntregadorResponseDTO;
import com.exemplo.pizzaria.mapper.EntregadorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntregadorService {
    
    private final EntregadorRepository entregadorRepository;
    private final EntregadorMapper entregadorMapper;
    
    @Transactional(readOnly = true)
    public List<EntregadorResponseDTO> findAll() {
        return entregadorRepository.findAll().stream()
                .map(entregadorMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public EntregadorResponseDTO findById(Long id) {
        Entregador entregador = entregadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entregador", id));
        return entregadorMapper.toResponse(entregador);
    }
    
    @Transactional
    public EntregadorResponseDTO create(EntregadorRequestDTO dto) {
        Entregador entregador = entregadorMapper.toEntity(dto);
        entregador = entregadorRepository.save(entregador);
        return entregadorMapper.toResponse(entregador);
    }
    
    @Transactional
    public EntregadorResponseDTO update(Long id, EntregadorRequestDTO dto) {
        Entregador entregador = entregadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entregador", id));
        
        entregadorMapper.updateEntityFromDTO(dto, entregador);
        entregador = entregadorRepository.save(entregador);
        return entregadorMapper.toResponse(entregador);
    }
    
    @Transactional(readOnly = true)
    public List<EntregadorResponseDTO> findDisponiveis() {
        return entregadorRepository.findByDisponivelTrue().stream()
                .map(entregadorMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public Entregador findEntityById(Long id) {
        return entregadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entregador", id));
    }
}

