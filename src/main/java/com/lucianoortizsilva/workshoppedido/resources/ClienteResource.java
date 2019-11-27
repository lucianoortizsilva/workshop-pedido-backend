package com.lucianoortizsilva.workshoppedido.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.workshoppedido.domain.Cliente;
import com.lucianoortizsilva.workshoppedido.dto.ClienteDTO;
import com.lucianoortizsilva.workshoppedido.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable(value = "id") Integer id) {
		Cliente cliente = this.clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}

	
	

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		final List<Cliente> objs = this.clienteService.findAll();
		final List<ClienteDTO> dtos = objs.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
	
	
	
	
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "qtdPaginas", defaultValue = "0") Integer qtdPaginas, 
													   @RequestParam(value = "qtdLinhas", defaultValue = "24") Integer qtdLinhas, 
													   @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
													   @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		final Page<Cliente> pageCliente = this.clienteService.findPage(qtdPaginas, qtdLinhas, orderBy, direction);
		final Page<ClienteDTO> pageClienteDTO = pageCliente.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(pageClienteDTO);
	}
	
	
	
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO dto, @PathVariable(value = "id") Integer id) {
		dto.setId(id);
		final Cliente obj = this.clienteService.fromDTO(dto);
		this.clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}

	
	
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable(value = "id") Integer id) {
		this.clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
