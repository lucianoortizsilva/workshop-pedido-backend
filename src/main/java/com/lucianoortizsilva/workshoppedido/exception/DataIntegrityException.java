package com.lucianoortizsilva.workshoppedido.exception;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -8979247949102244261L;

	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String msg, Throwable e) {
		super(msg);
	}
}
