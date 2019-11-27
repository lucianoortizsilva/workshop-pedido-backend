package com.lucianoortizsilva.workshoppedido.dto;

import java.io.Serializable;

import com.lucianoortizsilva.workshoppedido.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = -3404908941794028360L;

	private Integer id;
	private String nome;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
