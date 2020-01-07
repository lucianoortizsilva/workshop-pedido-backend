package com.lucianoortizsilva.workshoppedido.exception;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = -3623720129927238779L;

	public FileException(String msg) {
		super(msg);
	}

	public FileException(String msg, Throwable e) {
		super(msg);
	}
}
