package com.lucianoortizsilva.workshoppedido.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lucianoortizsilva.workshoppedido.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.mail.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine; 
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendConfirmacaoPedido(Pedido obj) {
		final SimpleMailMessage simpleMailMessage = prepararSimpleMailMessageFromPedido(obj);
		sendEmail(simpleMailMessage);
	}

	
	
	
	
	@Override
	public void sendConfirmacaoPedidoHtml(Pedido obj) {
		MimeMessage mimeMessage;
		try {
			mimeMessage = prepararMimeMessageFromPedido(obj);
			sendEmailHtml(mimeMessage);
		} catch (MessagingException e) {
			sendConfirmacaoPedido(obj);
		}
	}
	
	
	
	
	private MimeMessage prepararMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);		
		mimeMessageHelper.setTo(obj.getCliente().getEmail());
		mimeMessageHelper.setFrom(this.sender);
		mimeMessage.setSubject("Pedido Confirmado! Código: ".concat(obj.getId().toString()));
		mimeMessage.setSentDate(new Date(System.currentTimeMillis()));
		mimeMessage.setText(htmlFromTemplatePedido(obj), "UTF-8", "html");
		return mimeMessage;
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

	
	
	
	
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return this.templateEngine.process("email/confirmacaoPedido", context);		
	}

}
