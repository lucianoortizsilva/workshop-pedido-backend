package com.lucianoortizsilva.workshoppedido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.Categoria;
import com.lucianoortizsilva.workshoppedido.exception.DataIntegrityException;
import com.lucianoortizsilva.workshoppedido.exception.ObjectNotFoundException;
import com.lucianoortizsilva.workshoppedido.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	
	
	
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = this.repository.findById(id);
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
		}
		return obj.get();
	}

	
	
	
	
	public Categoria insert(final Categoria obj) {
		obj.setId(null);
		return this.repository.save(obj); 
	}

	
	
	
	
	public Categoria update(Categoria categoria) {
		this.find(categoria.getId());
		return this.repository.save(categoria);
	}
	
	
	
	
	
	public void delete(Integer id) {
		this.find(id);
		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}





	public List<Categoria> findAll() {
		return this.repository.findAll();
	}

}
