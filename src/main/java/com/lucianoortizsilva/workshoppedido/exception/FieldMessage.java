package com.lucianoortizsilva.workshoppedido.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = -5815227977851988101L;

	private String fieldName;
	private String message;

	public FieldMessage() {
		super();
	}

	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
