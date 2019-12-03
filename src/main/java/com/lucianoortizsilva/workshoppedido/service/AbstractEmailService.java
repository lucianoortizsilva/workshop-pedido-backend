package com.lucianoortizsilva.workshoppedido.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.lucianoortizsilva.workshoppedido.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.mail.sender}")
	private String sender;

	@Override
	public void sendConfirmacaoPedido(Pedido obj) {
		final SimpleMailMessage simpleMailMessage = prepararSimpleMailMessageFromPedido(obj);
		sendEmail(simpleMailMessage);
	}

	protected SimpleMailMessage prepararSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(obj.getCliente().getEmail());
		simpleMailMessage.setFrom(this.sender);
		simpleMailMessage.setSubject("Pedido Confirmado! Código: ".concat(obj.getId().toString()));
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText(obj.toString());
		return simpleMailMessage;
	}

}
