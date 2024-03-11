package br.com.fiapfood.external.api;

import br.com.fiapfood.adapters.dto.response.exceptions.ErrorMessage;
import br.com.fiapfood.application.exception.InvalidFieldException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log4j2
@RestControllerAdvice
public class ExceptionHandlerApi {

	public static final String INVALID_FIELDS = "Invalid Fields";

	@ExceptionHandler(value = { EntityNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage objectNotFoundException(Exception ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		return new ErrorMessage(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage());
	}

	@ExceptionHandler(value = { InvalidFieldException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage httpMessageNotReadableException(InvalidFieldException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		return new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage(),
				Collections.singletonList(ex.getCauseErrorList()));
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage constraintViolationException(ConstraintViolationException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		List<ErrorMessage.CauseError> causes = new ArrayList<>();
		for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
			var listField = constraint.getPropertyPath().toString().split("\\.");
			causes.add(ErrorMessage.CauseError.builder()
				.cause(constraint.getMessage())
				.field(listField[listField.length - 1])
				.build());
		}

		var message = new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(), INVALID_FIELDS);
		message.setDetail(causes);
		return message;
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage httpMessageNotReadableException(Exception ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		List<?> listss = new ArrayList<>();

		if (ex instanceof JsonParseException jsonEx) {
			listss = Collections.singletonList(jsonEx.getOriginalMessage());
		}
		else if (ex instanceof JsonMappingException jsonEx) {
			listss = jsonEx.getPath();
		}
		else if (ex instanceof HttpMessageNotReadableException jsonEx) {
			if (jsonEx.getRootCause() instanceof InvalidFieldException jsonInvalid) {
				listss = jsonInvalid.getCauseErrorList();
			}
			else {
				listss = Collections.singletonList(jsonEx.getMessage());
			}
		}
		return new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(), INVALID_FIELDS,
				Arrays.asList(listss.toArray()));
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		return new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(), INVALID_FIELDS,
				Arrays.asList(ex.getFieldErrors().toArray()));
	}

	@ExceptionHandler(value = { SQLException.class, DataIntegrityViolationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage sqlExceptionHandler(Exception ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		return new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Bad Request");

	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage getException(Exception ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), "Internal Server Error");
	}

}
