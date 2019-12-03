package com.lucianoortizsilva.workshoppedido.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.Cliente;
import com.lucianoortizsilva.workshoppedido.exception.ObjectNotFoundException;
import com.lucianoortizsilva.workshoppedido.repositories.ClienteRepository;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;

	private Random random = new Random();

	public void sendNewPassword(String email) {
		final Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado!");
		}

		String newSenha = newSenha();
		cliente.setSenha(this.bCryptPasswordEncoder.encode(newSenha));
		this.clienteRepository.save(cliente);
		this.emailService.sendNewPasswordEmail(cliente, newSenha);

	}

	private String newSenha() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if (opt == 0) {// gera um digito
			return (char) (this.random.nextInt(10) + 48);
		} else if (opt == 1) {// gera letra maiuscula
			return (char) (this.random.nextInt(26) + 65);
		} else {// gera letra minuscula
			return (char) (this.random.nextInt(26) + 97);
		}
	}

}
