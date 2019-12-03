package com.lucianoortizsilva.workshoppedido.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucianoortizsilva.workshoppedido.service.DBService;
import com.lucianoortizsilva.workshoppedido.service.EmailService;
import com.lucianoortizsilva.workshoppedido.service.MockEmailService;

@Configuration
@Profile("test")
public class ProfileTestConfig {

	@Autowired
	private DBService service;

	@Bean
	public boolean instantiateDataBase() throws ParseException {
		this.service.instantiateDataBase();
		return true;
	}

	@Bean
	public EmailService emailEmailService() {
		return new MockEmailService();
	}

}
