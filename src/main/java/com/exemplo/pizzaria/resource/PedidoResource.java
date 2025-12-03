package com.exemplo.pizzaria.resource;

import com.exemplo.pizzaria.domain.enums.StatusPedido;
import com.exemplo.pizzaria.dto.request.PedidoCreateRequestDTO;
import com.exemplo.pizzaria.dto.request.PedidoUpdateRequestDTO;
import com.exemplo.pizzaria.dto.response.PedidoResponseDTO;
import com.exemplo.pizzaria.domain.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoResource {
    
    private final PedidoService pedidoService;
    
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> findAll() {
        return ResponseEntity.ok(pedidoService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> create(@Valid @RequestBody PedidoCreateRequestDTO dto) {
        PedidoResponseDTO response = pedidoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PedidoUpdateRequestDTO dto) {
        return ResponseEntity.ok(pedidoService.update(id, dto));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido status) {
        return ResponseEntity.ok(pedidoService.updateStatus(id, status));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

