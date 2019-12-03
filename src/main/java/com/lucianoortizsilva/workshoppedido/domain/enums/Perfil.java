package com.lucianoortizsilva.workshoppedido.domain.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

	private int codigo;
	private String descricao;

	Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (final Perfil tc : Perfil.values()) {
			if (codigo.equals(tc.getCodigo())) {
				return tc;
			}
		}

		throw new IllegalArgumentException("Id Invalido: " + codigo);
	}

}
