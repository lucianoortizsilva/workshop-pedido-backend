package com.lucianoortizsilva.workshoppedido.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
	
}
