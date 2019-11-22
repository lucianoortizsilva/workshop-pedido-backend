package com.lucianoortizsilva.workshoppedido.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.Categoria;
import com.lucianoortizsilva.workshoppedido.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria getById(Integer id) {
		Optional<Categoria> categoria = this.repository.findById(id);
		return categoria.orElse(null);
	}

}
