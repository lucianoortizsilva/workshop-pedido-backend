package com.lucianoortizsilva.workshoppedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.Estado;
import com.lucianoortizsilva.workshoppedido.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public List<Estado> findAll() {
		return this.repo.findAllByOrderByNome();
	}
}
