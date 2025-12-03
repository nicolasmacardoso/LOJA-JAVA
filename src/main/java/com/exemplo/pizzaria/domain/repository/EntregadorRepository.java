package com.exemplo.pizzaria.domain.repository;

import com.exemplo.pizzaria.domain.entity.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Long> {
    
    List<Entregador> findByDisponivelTrue();
}

