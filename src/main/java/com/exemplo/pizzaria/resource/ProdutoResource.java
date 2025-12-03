package com.exemplo.pizzaria.resource;

import com.exemplo.pizzaria.dto.request.ProdutoRequestDTO;
import com.exemplo.pizzaria.dto.response.ProdutoResponseDTO;
import com.exemplo.pizzaria.domain.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoResource {
    
    private final ProdutoService produtoService;
    
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll() {
        return ResponseEntity.ok(produtoService.findAll());
    }
    
    @GetMapping("/disponiveis")
    public ResponseEntity<List<ProdutoResponseDTO>> findDisponiveis() {
        return ResponseEntity.ok(produtoService.findDisponiveis());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO response = produtoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(produtoService.update(id, dto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

