package com.lucianoortizsilva.workshoppedido.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.Pedido;
import com.lucianoortizsilva.workshoppedido.exception.ObjectNotFoundException;
import com.lucianoortizsilva.workshoppedido.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido getById(Integer id) {
		Optional<Pedido> obj = this.repository.findById(id);
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
		}
		return obj.get();
	}

}
