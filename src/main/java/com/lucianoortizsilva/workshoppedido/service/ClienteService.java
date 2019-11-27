package com.lucianoortizsilva.workshoppedido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.Cliente;
import com.lucianoortizsilva.workshoppedido.dto.ClienteDTO;
import com.lucianoortizsilva.workshoppedido.exception.DataIntegrityException;
import com.lucianoortizsilva.workshoppedido.exception.ObjectNotFoundException;
import com.lucianoortizsilva.workshoppedido.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = this.repository.findById(id);
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
		}
		return obj.get();
	}

	public List<Cliente> findAll() {
		return this.repository.findAll();
	}

	public Page<Cliente> findPage(Integer qtdPaginas, Integer qtdLinhas, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(qtdPaginas, qtdLinhas, Direction.valueOf(direction), orderBy);
		return this.repository.findAll(pageRequest);
	}

	public Cliente update(Cliente obj) {
		final Cliente newObj = this.find(obj.getId());
		updateData(newObj, obj);
		return this.repository.save(newObj);
	}

	private static void updateData(final Cliente newObj, final Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail()); 
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}

}
