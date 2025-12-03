package com.exemplo.pizzaria.domain.repository;

import com.exemplo.pizzaria.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByDisponivelTrue();
    
    List<Produto> findByCategoriaAndDisponivelTrue(com.exemplo.pizzaria.domain.enums.Categoria categoria);
}

