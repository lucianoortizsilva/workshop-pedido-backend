package com.lucianoortizsilva.workshoppedido.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lucianoortizsilva.workshoppedido.domain.Categoria;
import com.lucianoortizsilva.workshoppedido.domain.Produto;
import com.lucianoortizsilva.workshoppedido.repositories.CategoriaRepository;
import com.lucianoortizsilva.workshoppedido.repositories.ProdutoRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository; 

		
	@Override
	public void run(String... args) throws Exception {

		this.categoriaRepository.deleteAll();
		
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		categoria2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(categoria1));
		p2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		p3.getCategorias().addAll(Arrays.asList(categoria1));
		
		this.categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		this.produtoRepository.saveAll(Arrays.asList(p1, p2, p3));		

	}

}
