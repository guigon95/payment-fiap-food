package br.com.fiapfood.application.exception;

import br.com.fiapfood.adapters.dto.response.exceptions.ErrorMessage;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class InvalidFieldException extends RuntimeException implements Serializable {

	final String message;

	final transient List<ErrorMessage.CauseError> causeErrorList;

}
