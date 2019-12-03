package com.lucianoortizsilva.workshoppedido.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucianoortizsilva.workshoppedido.service.DBService;
import com.lucianoortizsilva.workshoppedido.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class ProfileDevConfig {

	@Autowired
	private DBService service;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDataBase() throws ParseException {

		if ("create".equals(this.strategy) || "create-drop".equals(this.strategy)) {
			this.service.instantiateDataBase();
			return true;
		} else {
			return false;
		}

	}

	@Bean
	public SmtpEmailService emailEmailService() {
		return new SmtpEmailService();
	}

}
