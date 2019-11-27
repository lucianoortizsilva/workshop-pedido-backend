package com.lucianoortizsilva.workshoppedido.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 7559055929703116325L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
