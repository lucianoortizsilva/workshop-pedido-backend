package com.lucianoortizsilva.workshoppedido.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.lucianoortizsilva.workshoppedido.domain.enums.Perfil;
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
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	public void instantiateDataBase() throws ParseException  {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		Categoria categoria3 = new Categoria(null, "Chocolate");
		Categoria categoria4 = new Categoria(null, "Biscoito");
		Categoria categoria5 = new Categoria(null, "Bebida");
		Categoria categoria6 = new Categoria(null, "Elétrico");
		Categoria categoria7 = new Categoria(null, "Ferragem");
		Categoria categoria8 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escrotório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Copo", 180.00);
		Produto p11 = new Produto(null, "Shampo", 90.00);
		Produto p12 = new Produto(null, "Produto 12", 10.00);
		Produto p13 = new Produto(null, "Produto 13", 10.00);
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);

		Estado estado1 = new Estado(null, "Rio Grande Do Sul");
		Estado estado2 = new Estado(null, "Santa Catarina");

		Cidade cidade1 = new Cidade(null, "Porto Alegre", estado1);
		Cidade cidade2 = new Cidade(null, "Balneário Camboriú", estado2);
		Cidade cidade3 = new Cidade(null, "Itajaí", estado2);

		Cliente cliente1 = new Cliente(null, "Luciano Ortiz", "luciano.dev.tests@gmail.com", "254159774-54", TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123456"));
		cliente1.getTelefones().addAll(Arrays.asList("5165982541", "5195126566"));
		
		Cliente cliente2 = new Cliente(null, "Luciano Admin", "lucianoortizsilva@gmail.com", "316283827-40", TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123456"));
		cliente2.getTelefones().addAll(Arrays.asList("5165465465", "51951545454"));
		cliente2.addPerfil(Perfil.ADMIN);

		Endereco endereco1 = new Endereco(null, "Rua abc", "123", "fundos", "centro", "91452154", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Rua def", "458", "frente", "ladeira", "58745112", cliente1, cidade2);
		Endereco endereco3 = new Endereco(null, "Rua juju", "5454", "frente", "vila", "47745474", cliente2, cidade2);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:39"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("30/09/2017 19:35"), cliente1, endereco2);

		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/11/2017 00:00"), null);

		ItemPedido itemPedido1 = new ItemPedido(pedido1, p1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, p3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, p2, 100.00, 1, 800.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		categoria2.getProdutos().addAll(Arrays.asList(p2, p4));
		categoria3.getProdutos().addAll(Arrays.asList(p5, p6));
		categoria4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		categoria5.getProdutos().addAll(Arrays.asList(p8));
		categoria6.getProdutos().addAll(Arrays.asList(p9, p10));
		categoria7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, categoria4));
		p2.getCategorias().addAll(Arrays.asList(cat1, categoria2, categoria4));
		p3.getCategorias().addAll(Arrays.asList(cat1, categoria4));
		p4.getCategorias().addAll(Arrays.asList(categoria2));
		p5.getCategorias().addAll(Arrays.asList(categoria3));
		p6.getCategorias().addAll(Arrays.asList(categoria3));
		p7.getCategorias().addAll(Arrays.asList(categoria4));
		p8.getCategorias().addAll(Arrays.asList(categoria5));
		p9.getCategorias().addAll(Arrays.asList(categoria6));
		p10.getCategorias().addAll(Arrays.asList(categoria6));
		p11.getCategorias().addAll(Arrays.asList(categoria7));
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));

		p1.getItens().addAll(Arrays.asList(itemPedido1));
		p2.getItens().addAll(Arrays.asList(itemPedido3));
		p3.getItens().addAll(Arrays.asList(itemPedido2));

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		cliente2.getEnderecos().addAll(Arrays.asList(endereco3));

		pedido1.setPagamento(pagamento1);
		pedido2.setPagamento(pagamento2);

		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

		this.categoriaRepository.saveAll(Arrays.asList(cat1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7, categoria8));
		this.produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		this.estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		this.cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		this.clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		this.enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3));
		this.pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		this.pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		this.itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
	}

}
