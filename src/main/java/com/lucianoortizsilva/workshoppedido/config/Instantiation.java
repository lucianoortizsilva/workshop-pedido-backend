package com.lucianoortizsilva.workshoppedido.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lucianoortizsilva.workshoppedido.domain.Categoria;
import com.lucianoortizsilva.workshoppedido.domain.Cidade;
import com.lucianoortizsilva.workshoppedido.domain.Cliente;
import com.lucianoortizsilva.workshoppedido.domain.Endereco;
import com.lucianoortizsilva.workshoppedido.domain.Estado;
import com.lucianoortizsilva.workshoppedido.domain.Produto;
import com.lucianoortizsilva.workshoppedido.domain.enums.TipoCliente;
import com.lucianoortizsilva.workshoppedido.repositories.CategoriaRepository;
import com.lucianoortizsilva.workshoppedido.repositories.CidadeRepository;
import com.lucianoortizsilva.workshoppedido.repositories.ClienteRepository;
import com.lucianoortizsilva.workshoppedido.repositories.EnderecoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.EstadoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.ProdutoRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void run(String... args) throws Exception {

		this.categoriaRepository.deleteAll();
		this.produtoRepository.deleteAll();

		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		Estado estado1 = new Estado(null, "Rio Grande Do Sul");
		Estado estado2 = new Estado(null, "Santa Catarina");

		Cidade cidade1 = new Cidade(null, "Porto Alegre", estado1);
		Cidade cidade2 = new Cidade(null, "Balneário Camboriú", estado2);
		Cidade cidade3 = new Cidade(null, "Itajaí", estado2);

		Cliente cliente1 = new Cliente(null, "Luciano Ortiz", "luciano@gamil.com", "254159774-54", TipoCliente.PESSOA_FISICA);
		cliente1.getTelefones().addAll(Arrays.asList("5165982541", "5195126566"));

		Endereco endereco1 = new Endereco(null, "Rua abc", "123", "fundos", "centro", "91452154", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Rua def", "458", "frente", "ladeira", "58745112", cliente1, cidade2);

		categoria1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		categoria2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(categoria1));
		p2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		p3.getCategorias().addAll(Arrays.asList(categoria1));

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		this.categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		this.produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		this.estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		this.cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		this.clienteRepository.saveAll(Arrays.asList(cliente1));
		this.enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
	}

}
