package com.lucianoortizsilva.workshoppedido.exception;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = -3623720129927238779L;

	public AuthorizationException(String msg) {
		super(msg);
	}

	public AuthorizationException(String msg, Throwable e) {
		super(msg);
	}
}
