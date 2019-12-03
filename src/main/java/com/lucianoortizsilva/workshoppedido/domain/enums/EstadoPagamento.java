package com.lucianoortizsilva.workshoppedido.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private int codigo;
	private String descricao;

	EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (final EstadoPagamento tc : EstadoPagamento.values()) {
			if (codigo.equals(tc.getCodigo())) {
				return tc;
			}
		}

		throw new IllegalArgumentException("Id Invalido: " + codigo);
	}

}
