package com.lucianoortizsilva.workshoppedido.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucianoortizsilva.workshoppedido.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {}
