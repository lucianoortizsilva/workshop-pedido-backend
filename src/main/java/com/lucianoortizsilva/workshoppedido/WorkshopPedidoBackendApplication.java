package com.lucianoortizsilva.workshoppedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucianoortizsilva.workshoppedido.service.S3Service;

@SpringBootApplication
public class WorkshopPedidoBackendApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(WorkshopPedidoBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.s3Service.uploadFile("E:\\saci.png");
	}

}
