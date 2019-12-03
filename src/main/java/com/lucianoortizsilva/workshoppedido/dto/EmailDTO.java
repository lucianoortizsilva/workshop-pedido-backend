package com.lucianoortizsilva.workshoppedido.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 8190097752609021830L;

	@Email(message = "E-mail Inválido")
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
