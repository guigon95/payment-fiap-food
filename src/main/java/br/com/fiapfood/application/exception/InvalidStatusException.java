package br.com.fiapfood.application.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidStatusException extends RuntimeException {

	public InvalidStatusException(String message) {
		super(message);
	}

}
