package br.com.fiapfood.application.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ObjectException extends RuntimeException {

	public ObjectException(String message) {
		super(message);
	}

}
