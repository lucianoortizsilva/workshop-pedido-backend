package com.lucianoortizsilva.workshoppedido.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		final HttpStatus status =  HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	
	
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		final HttpStatus status =  HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Restrição de integridade", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		final HttpStatus status =  HttpStatus.BAD_REQUEST;
		final ValidationError validationError = new ValidationError(System.currentTimeMillis(), status.value(), "Erro de validação", "Erro de validação", request.getRequestURI());
		for(FieldError fe: e.getBindingResult().getFieldErrors()) {
			validationError.addError(fe.getField(), fe.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(validationError);
	}

	
	
	
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		final HttpStatus status =  HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não Autorizado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {
		final HttpStatus status =  HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Erro de IO", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	
	
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> file(AmazonServiceException e, HttpServletRequest request) {
		final HttpStatus status =  HttpStatus.valueOf(e.getErrorCode());
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Erro Amazon", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	
	
	
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> file(AmazonClientException e, HttpServletRequest request) {
		final HttpStatus status =  HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Erro Amazon", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	
	
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> file(AmazonS3Exception e, HttpServletRequest request) {
		final HttpStatus status =  HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Erro Amazon", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
}
