package com.lucianoortizsilva.workshoppedido.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucianoortizsilva.workshoppedido.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {}
