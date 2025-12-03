package com.exemplo.pizzaria.resource;

import com.exemplo.pizzaria.dto.request.EntregadorRequestDTO;
import com.exemplo.pizzaria.dto.response.EntregadorResponseDTO;
import com.exemplo.pizzaria.domain.service.EntregadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregadores")
@RequiredArgsConstructor
public class EntregadorResource {
    
    private final EntregadorService entregadorService;
    
    @GetMapping
    public ResponseEntity<List<EntregadorResponseDTO>> findAll() {
        return ResponseEntity.ok(entregadorService.findAll());
    }
    
    @GetMapping("/disponiveis")
    public ResponseEntity<List<EntregadorResponseDTO>> findDisponiveis() {
        return ResponseEntity.ok(entregadorService.findDisponiveis());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EntregadorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(entregadorService.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<EntregadorResponseDTO> create(@Valid @RequestBody EntregadorRequestDTO dto) {
        EntregadorResponseDTO response = entregadorService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EntregadorResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody EntregadorRequestDTO dto) {
        return ResponseEntity.ok(entregadorService.update(id, dto));
    }
}

