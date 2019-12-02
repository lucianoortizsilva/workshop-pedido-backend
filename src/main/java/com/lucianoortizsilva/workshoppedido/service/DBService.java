package com.lucianoortizsilva.workshoppedido.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.Categoria;
import com.lucianoortizsilva.workshoppedido.domain.Cidade;
import com.lucianoortizsilva.workshoppedido.domain.Cliente;
import com.lucianoortizsilva.workshoppedido.domain.Endereco;
import com.lucianoortizsilva.workshoppedido.domain.Estado;
import com.lucianoortizsilva.workshoppedido.domain.ItemPedido;
import com.lucianoortizsilva.workshoppedido.domain.Pagamento;
import com.lucianoortizsilva.workshoppedido.domain.PagamentoComBoleto;
import com.lucianoortizsilva.workshoppedido.domain.PagamentoComCartao;
import com.lucianoortizsilva.workshoppedido.domain.Pedido;
import com.lucianoortizsilva.workshoppedido.domain.Produto;
import com.lucianoortizsilva.workshoppedido.domain.enums.EstadoPagamento;
import com.lucianoortizsilva.workshoppedido.domain.enums.TipoCliente;
import com.lucianoortizsilva.workshoppedido.repositories.CategoriaRepository;
import com.lucianoortizsilva.workshoppedido.repositories.CidadeRepository;
import com.lucianoortizsilva.workshoppedido.repositories.ClienteRepository;
import com.lucianoortizsilva.workshoppedido.repositories.EnderecoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.EstadoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.ItemPedidoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.PagamentoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.PedidoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.ProdutoRepository;

@Service
public class DBService {

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

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	
	public void instantiateDataBase() throws ParseException  {

		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		Categoria categoria3 = new Categoria(null, "Chocolate");
		Categoria categoria4 = new Categoria(null, "Biscoito");
		Categoria categoria5 = new Categoria(null, "Bebida");
		Categoria categoria6 = new Categoria(null, "Elétrico");
		Categoria categoria7 = new Categoria(null, "Ferragem");
		Categoria categoria8 = new Categoria(null, "Perfumaria");

		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		Produto prod4 = new Produto(null, "Mesa de escrotório", 300.00);
		Produto prod5 = new Produto(null, "Toalha", 50.00);
		Produto prod6 = new Produto(null, "Colcha", 200.00);
		Produto prod7 = new Produto(null, "TV true color", 1200.00);
		Produto prod8 = new Produto(null, "Roçadeira", 800.00);
		Produto prod9 = new Produto(null, "Abajour", 100.00);
		Produto prod10 = new Produto(null, "Copo", 180.00);
		Produto prod11 = new Produto(null, "Shampo", 90.00);

		Estado estado1 = new Estado(null, "Rio Grande Do Sul");
		Estado estado2 = new Estado(null, "Santa Catarina");

		Cidade cidade1 = new Cidade(null, "Porto Alegre", estado1);
		Cidade cidade2 = new Cidade(null, "Balneário Camboriú", estado2);
		Cidade cidade3 = new Cidade(null, "Itajaí", estado2);

		Cliente cliente1 = new Cliente(null, "Luciano Ortiz", "luciano@gamil.com", "254159774-54",
				TipoCliente.PESSOA_FISICA);
		cliente1.getTelefones().addAll(Arrays.asList("5165982541", "5195126566"));

		Endereco endereco1 = new Endereco(null, "Rua abc", "123", "fundos", "centro", "91452154", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Rua def", "458", "frente", "ladeira", "58745112", cliente1, cidade2);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:39"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("30/09/2017 19:35"), cliente1, endereco2);

		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/11/2017 00:00"), null);

		ItemPedido itemPedido1 = new ItemPedido(pedido1, prod1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, prod3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, prod2, 100.00, 1, 800.00);

		categoria1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		categoria2.getProdutos().addAll(Arrays.asList(prod2, prod4));
		categoria3.getProdutos().addAll(Arrays.asList(prod5, prod6));
		categoria4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		categoria5.getProdutos().addAll(Arrays.asList(prod8));
		categoria6.getProdutos().addAll(Arrays.asList(prod9, prod10));
		categoria7.getProdutos().addAll(Arrays.asList(prod11));

		prod1.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
		prod2.getCategorias().addAll(Arrays.asList(categoria1, categoria2, categoria4));
		prod3.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
		prod4.getCategorias().addAll(Arrays.asList(categoria2));
		prod5.getCategorias().addAll(Arrays.asList(categoria3));
		prod6.getCategorias().addAll(Arrays.asList(categoria3));
		prod7.getCategorias().addAll(Arrays.asList(categoria4));
		prod8.getCategorias().addAll(Arrays.asList(categoria5));
		prod9.getCategorias().addAll(Arrays.asList(categoria6));
		prod10.getCategorias().addAll(Arrays.asList(categoria6));
		prod11.getCategorias().addAll(Arrays.asList(categoria7));

		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));

		prod1.getItens().addAll(Arrays.asList(itemPedido1));
		prod2.getItens().addAll(Arrays.asList(itemPedido3));
		prod3.getItens().addAll(Arrays.asList(itemPedido2));

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		pedido1.setPagamento(pagamento1);
		pedido2.setPagamento(pagamento2);

		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

		this.categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7, categoria8));
		this.produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));
		this.estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		this.cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		this.clienteRepository.saveAll(Arrays.asList(cliente1));
		this.enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		this.pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		this.pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		this.itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
	}

}