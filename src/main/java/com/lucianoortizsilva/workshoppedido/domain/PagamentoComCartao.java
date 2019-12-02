package com.lucianoortizsilva.workshoppedido.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.lucianoortizsilva.workshoppedido.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName(value = "pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = -1524301041831108305L;
	private Integer numeroParcelas;

	public PagamentoComCartao() {
		super();
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
		super(id, estadoPagamento, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

}
