package com.lucianoortizsilva.workshoppedido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.Categoria;
import com.lucianoortizsilva.workshoppedido.domain.Produto;
import com.lucianoortizsilva.workshoppedido.exception.ObjectNotFoundException;
import com.lucianoortizsilva.workshoppedido.repositories.CategoriaRepository;
import com.lucianoortizsilva.workshoppedido.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> obj = this.repository.findById(id);
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
		}
		return obj.get();
	}
	
	
	
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer qtdPaginas, Integer qtdLinhas, String orderBy, String direction) {
		final PageRequest pageRequest = PageRequest.of(qtdPaginas, qtdLinhas, Direction.valueOf(direction), orderBy);
		final List<Categoria> categorias = this.categoriaRepository.findAllById(ids); 
		return this.repository.search(nome, categorias, pageRequest);
	}

}
