package br.com.fiapfood.application.exception;

import br.com.fiapfood.adapters.dto.response.exceptions.ErrorMessage;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class InvalidFieldException extends RuntimeException implements Serializable {

	String message;

	transient List<ErrorMessage.CauseError> causeErrorList;

}
