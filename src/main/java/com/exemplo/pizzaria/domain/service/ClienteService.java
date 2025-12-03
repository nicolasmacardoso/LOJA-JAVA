package com.exemplo.pizzaria.domain.service;

import com.exemplo.pizzaria.domain.entity.Cliente;
import com.exemplo.pizzaria.domain.exception.BadRequestException;
import com.exemplo.pizzaria.domain.exception.EntityNotFoundException;
import com.exemplo.pizzaria.domain.repository.ClienteRepository;
import com.exemplo.pizzaria.dto.request.ClienteRequestDTO;
import com.exemplo.pizzaria.dto.response.ClienteResponseDTO;
import com.exemplo.pizzaria.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente", id));
        return clienteMapper.toResponse(cliente);
    }
    
    @Transactional
    public ClienteResponseDTO create(ClienteRequestDTO dto) {
        // Valida��o de CPF �nico
        if (dto.getCpf() != null && !dto.getCpf().isEmpty()) {
            if (clienteRepository.existsByCpf(dto.getCpf())) {
                throw new BadRequestException("CPF j� cadastrado");
            }
        }
        
        // Valida��o de email �nico
        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            if (clienteRepository.existsByEmail(dto.getEmail())) {
                throw new BadRequestException("Email j� cadastrado");
            }
        }
        
        Cliente cliente = clienteMapper.toEntity(dto);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toResponse(cliente);
    }
    
    @Transactional
    public ClienteResponseDTO update(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente", id));
        
        // Valida��o de CPF �nico (se alterado)
        if (dto.getCpf() != null && !dto.getCpf().isEmpty() && !dto.getCpf().equals(cliente.getCpf())) {
            if (clienteRepository.existsByCpf(dto.getCpf())) {
                throw new BadRequestException("CPF j� cadastrado");
            }
        }
        
        // Valida��o de email �nico (se alterado)
        if (dto.getEmail() != null && !dto.getEmail().isEmpty() && !dto.getEmail().equals(cliente.getEmail())) {
            if (clienteRepository.existsByEmail(dto.getEmail())) {
                throw new BadRequestException("Email j� cadastrado");
            }
        }
        
        clienteMapper.updateEntityFromDTO(dto, cliente);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toResponse(cliente);
    }
    
    @Transactional
    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente", id));

        // VALIDAÇÃO: Impedir deleção se tiver pedidos
        if (cliente.getPedidos() != null && !cliente.getPedidos().isEmpty()) {
            throw new BadRequestException(
                    String.format("Não é possível deletar o cliente '%s'. Existem %d pedido(s) associado(s) a este cliente.",
                            cliente.getNome(),
                            cliente.getPedidos().size())
            );
        }
        clienteRepository.delete(cliente);
    }
    
    @Transactional(readOnly = true)
    public Cliente findEntityById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente", id));
    }
}

