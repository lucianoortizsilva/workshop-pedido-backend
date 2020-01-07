package com.lucianoortizsilva.workshoppedido.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${s3.bucket}")
	private String bucketName;

	private Logger LOGGER = LoggerFactory.getLogger(S3Service.class);

	public void uploadFile(String localFile) {
		try {
			LOGGER.info("Iniciando UPLOAD");
			final File file = new File(localFile);
			this.amazonS3.putObject(new PutObjectRequest(this.bucketName, "saci.png", file));
			LOGGER.info("UPLOAD Finalizado com sucesso!");
		} catch (final AmazonServiceException e) {
			LOGGER.info("AmazonServiceException: ", e.getErrorMessage());
			LOGGER.info("Status code:", e.getErrorCode());
		} catch (final AmazonClientException e) {
			LOGGER.info("AmazonClientException:", e.getMessage());
		}
	}

}
