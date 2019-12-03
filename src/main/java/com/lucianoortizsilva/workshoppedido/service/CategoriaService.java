package com.lucianoortizsilva.workshoppedido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.Categoria;
import com.lucianoortizsilva.workshoppedido.dto.CategoriaDTO;
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

	public List<Categoria> findAll() {
		return this.repository.findAll();
	}

	public Page<Categoria> findPage(Integer qtdPaginas, Integer qtdLinhas, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(qtdPaginas, qtdLinhas, Direction.valueOf(direction), orderBy);
		return this.repository.findAll(pageRequest);
	}

	public Categoria insert(final Categoria obj) {
		return this.repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		final Categoria newObj = this.find(obj.getId());
		updateData(newObj, obj);
		return this.repository.save(newObj);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	private static void updateData(final Categoria newObj, final Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}
