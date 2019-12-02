package com.lucianoortizsilva.workshoppedido.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucianoortizsilva.workshoppedido.domain.Pedido;
import com.lucianoortizsilva.workshoppedido.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> find(@PathVariable(value = "id") Integer id) {
		Pedido pedido = this.pedidoService.find(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	
	
	
	
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = this.pedidoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	
	
	
}
