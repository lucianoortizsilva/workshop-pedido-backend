package com.lucianoortizsilva.workshoppedido.service;

import org.springframework.mail.SimpleMailMessage;

import com.lucianoortizsilva.workshoppedido.domain.Pedido;

public interface EmailService {

	void sendConfirmacaoPedido(Pedido obj);

	void sendEmail(SimpleMailMessage simpleMailMessage);

}
