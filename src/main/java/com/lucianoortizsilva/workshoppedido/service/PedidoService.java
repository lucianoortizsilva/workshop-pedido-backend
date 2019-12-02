package com.lucianoortizsilva.workshoppedido.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.ItemPedido;
import com.lucianoortizsilva.workshoppedido.domain.PagamentoComBoleto;
import com.lucianoortizsilva.workshoppedido.domain.Pedido;
import com.lucianoortizsilva.workshoppedido.domain.Produto;
import com.lucianoortizsilva.workshoppedido.domain.enums.EstadoPagamento;
import com.lucianoortizsilva.workshoppedido.exception.ObjectNotFoundException;
import com.lucianoortizsilva.workshoppedido.repositories.ItemPedidoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.PagamentoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.PedidoRepository;
import com.lucianoortizsilva.workshoppedido.repositories.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = this.repository.findById(id);
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
		}
		return obj.get();
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
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
			Optional<Produto> p =  this.produtoRepository.findById(i.getProduto().getId());
			i.setPreco(p.get().getPreco());
			i.setPedido(obj);
		}

		this.itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
	}

}
