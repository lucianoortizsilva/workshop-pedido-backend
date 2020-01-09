package com.lucianoortizsilva.workshoppedido.dto;

import java.io.Serializable;

import com.lucianoortizsilva.workshoppedido.domain.Estado;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = -1871166100845865774L;

	private Integer id;
	private String nome;

	public EstadoDTO() {
	}

	public EstadoDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public EstadoDTO(Estado obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
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
