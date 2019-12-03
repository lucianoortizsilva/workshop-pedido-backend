package com.lucianoortizsilva.workshoppedido.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucianoortizsilva.workshoppedido.domain.ItemPedido;
import com.lucianoortizsilva.workshoppedido.domain.PagamentoComBoleto;
import com.lucianoortizsilva.workshoppedido.domain.Pedido;
import com.lucianoortizsilva.workshoppedido.domain.enums.EstadoPagamento;
import com.lucianoortizsilva.workshoppedido.exception.ObjectNotFoundException;
import com.lucianoortizsilva.workshoppedido.repositories.ItemPedidoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.PagamentoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService; 

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = this.repository.findById(id);
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
		}
		return obj.get();
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(this.clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) obj.getPagamento();
			this.boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, obj.getInstante());
		}

		obj = this.repository.save(obj);
		this.pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido i : obj.getItens()) {
			i.setDesconto(0.0);
			i.setProduto(this.produtoService.find(i.getProduto().getId()));
			i.setPreco(i.getProduto().getPreco());
			i.setPedido(obj);
		}

		this.itemPedidoRepository.saveAll(obj.getItens());
		
		this.emailService.sendConfirmacaoPedidoHtml(obj);
		
		return obj;
	}

}
