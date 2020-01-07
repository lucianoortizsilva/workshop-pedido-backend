package com.lucianoortizsilva.workshoppedido.service;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${s3.bucket}")
	private String bucketName;

	private Logger LOGGER = LoggerFactory.getLogger(S3Service.class);

	public URI uploadFile(MultipartFile multipartFile) {
		URI uri = null;
		try {
			final String filename = multipartFile.getOriginalFilename();
			final InputStream is = multipartFile.getInputStream();
			final String contentType = multipartFile.getContentType();
			uri = this.uploadFile(is, filename, contentType);
		} catch (Exception e) {
			LOGGER.error("Erro de IO: ", e.getMessage());
		}
		return uri;
	}

	public URI uploadFile(InputStream is, String filename, String contentType) {
		URI uri = null;
		try {
			LOGGER.info("UPLOAD iniciando!");
			final ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(contentType);
			this.amazonS3.putObject(this.bucketName, filename, is, objectMetadata);
			uri = this.amazonS3.getUrl(this.bucketName, filename).toURI();
			LOGGER.info("UPLOAD Finalizado com sucesso!");
		} catch (final AmazonServiceException e) {
			LOGGER.info("AmazonServiceException: ", e.getErrorMessage());
			LOGGER.info("Status code:", e.getErrorCode());
		} catch (final AmazonClientException e) {
			LOGGER.info("AmazonClientException:", e.getMessage());
		} catch (final URISyntaxException e) {
			LOGGER.info("URISyntaxException:", e.getMessage());
		}
		return uri;
	}
}
