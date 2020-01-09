package com.lucianoortizsilva.workshoppedido.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.workshoppedido.domain.Cidade;
import com.lucianoortizsilva.workshoppedido.domain.Estado;
import com.lucianoortizsilva.workshoppedido.dto.CidadeDTO;
import com.lucianoortizsilva.workshoppedido.dto.EstadoDTO;
import com.lucianoortizsilva.workshoppedido.service.CidadeService;
import com.lucianoortizsilva.workshoppedido.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> estados = this.estadoService.findAll();
		List<EstadoDTO> dtos = estados.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}

	@GetMapping(value = "{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> list = this.cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
