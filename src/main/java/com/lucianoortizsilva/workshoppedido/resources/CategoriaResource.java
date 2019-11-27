package com.lucianoortizsilva.workshoppedido.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucianoortizsilva.workshoppedido.domain.Categoria;
import com.lucianoortizsilva.workshoppedido.dto.CategoriaDTO;
import com.lucianoortizsilva.workshoppedido.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	
	
	
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		final List<Categoria> categorias = this.categoriaService.findAll();
		final List<CategoriaDTO> categoriaDTOs = categorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriaDTOs);
	}
	
	
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable(value = "id") Integer id) {
		Categoria categoria = this.categoriaService.find(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	
	
	
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "qtdPaginas", defaultValue = "0") Integer qtdPaginas, 
													   @RequestParam(value = "qtdLinhas", defaultValue = "24") Integer qtdLinhas, 
													   @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
													   @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		final Page<Categoria> pageCategoria = this.categoriaService.findPage(qtdPaginas, qtdLinhas, orderBy, direction);
		final Page<CategoriaDTO> pageCategoriaDTO = pageCategoria.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(pageCategoriaDTO);
	}
	
	
	
	
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO dto) {
		Categoria categoria = this.categoriaService.fromDTO(dto);
		categoria = this.categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO dto, @PathVariable(value = "id") Integer id) {
		dto.setId(id);
		final Categoria categoria = this.categoriaService.fromDTO(dto);
		this.categoriaService.update(categoria);
		return ResponseEntity.noContent().build();
	}

	
	
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable(value = "id") Integer id) {
		this.categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
