package com.lucianoortizsilva.workshoppedido.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.workshoppedido.domain.Produto;
import com.lucianoortizsilva.workshoppedido.dto.ProdutoDTO;
import com.lucianoortizsilva.workshoppedido.resources.utils.URL;
import com.lucianoortizsilva.workshoppedido.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService; 

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable(value = "id") Integer id) {
		Produto produto = this.produtoService.find(id);
		return ResponseEntity.ok().body(produto);
	}

	
	
	
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "")         String nome,
													 @RequestParam(value = "categorias", defaultValue = "")   String categorias, 
													 @RequestParam(value = "qtdPaginas", defaultValue = "0")  Integer qtdPaginas, 
													 @RequestParam(value = "qtdLinhas", defaultValue = "24")  Integer qtdLinhas, 
													 @RequestParam(value = "orderBy", defaultValue = "nome")  String orderBy, 
													 @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		final String nomeDecoded = URL.decodeParam(nome);
		final List<Integer> idsCategorias = URL.decodeIntList(categorias);
		final Page<Produto> pageProduto= this.produtoService.search(nomeDecoded, idsCategorias, qtdPaginas, qtdLinhas, orderBy, direction);
		final Page<ProdutoDTO> pageProdutoDTO = pageProduto.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(pageProdutoDTO);
	}
	
}
