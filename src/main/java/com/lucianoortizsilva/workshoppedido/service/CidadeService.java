package com.lucianoortizsilva.workshoppedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.Cidade;
import com.lucianoortizsilva.workshoppedido.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public List<Cidade> findByEstado(Integer estadoId) {
		return this.repo.findCidades(estadoId);
	}

}
