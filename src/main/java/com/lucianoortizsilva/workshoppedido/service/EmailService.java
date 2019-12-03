package com.lucianoortizsilva.workshoppedido.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.lucianoortizsilva.workshoppedido.domain.Pedido;

public interface EmailService {

	void sendConfirmacaoPedido(Pedido obj);
	void sendEmail(SimpleMailMessage simpleMailMessage);
	
	void sendConfirmacaoPedidoHtml(Pedido obj);
	void sendEmailHtml(MimeMessage mimeMessage);
	

}
