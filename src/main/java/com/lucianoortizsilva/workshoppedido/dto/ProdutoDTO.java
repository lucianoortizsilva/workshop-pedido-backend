package com.lucianoortizsilva.workshoppedido.dto;

import java.io.Serializable;

import com.lucianoortizsilva.workshoppedido.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1145212133740787594L;
	private Integer id;
	private String nome;
	private Double preco;

	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(final Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
