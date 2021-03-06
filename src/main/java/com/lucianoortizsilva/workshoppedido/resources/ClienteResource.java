package com.lucianoortizsilva.workshoppedido.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucianoortizsilva.workshoppedido.domain.Cliente;
import com.lucianoortizsilva.workshoppedido.dto.ClienteDTO;
import com.lucianoortizsilva.workshoppedido.dto.ClienteNewDTO;
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
	
	
	
	@GetMapping(value = "/email")
	public ResponseEntity<Cliente> find(@RequestParam(value = "value") String email) {
		Cliente cliente = this.clienteService.findByEmail(email);
		return ResponseEntity.ok().body(cliente);
	}

	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		final List<Cliente> objs = this.clienteService.findAll();
		final List<ClienteDTO> dtos = objs.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
	
	
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
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

	
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable(value = "id") Integer id) {
		this.clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO dto) {
		Cliente obj = this.clienteService.fromDTO(dto);
		obj = this.clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	
	
	@PostMapping(value = "/picture")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
		URI uri = this.clienteService.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}

}
