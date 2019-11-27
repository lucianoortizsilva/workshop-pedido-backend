package com.lucianoortizsilva.workshoppedido.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lucianoortizsilva.workshoppedido.domain.Cliente;
import com.lucianoortizsilva.workshoppedido.service.validation.ClienteUpdateValidator;

@ClienteUpdateValidator
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 7060721660274461708L;

	private Integer id;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	@Email(message = "E-mail Inválido")
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String email;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
