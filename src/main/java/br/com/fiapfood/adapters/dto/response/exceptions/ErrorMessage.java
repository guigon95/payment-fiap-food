package br.com.fiapfood.adapters.dto.response.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorMessage {

	HttpStatus httpStatus;

	LocalDateTime localDateTime;

	String message;

	List<CauseError> detail;

	public ErrorMessage(HttpStatus httpStatus, LocalDateTime now, String message) {
		this.httpStatus = httpStatus;
		this.localDateTime = now;
		this.message = message;
	}

	@Builder
	@AllArgsConstructor
	public static class CauseError {

		@JsonProperty("field")
		String field;

		@JsonProperty("cause")
		String cause;

	}

	public ErrorMessage(HttpStatus httpStatus, LocalDateTime localDateTime, String message, List<Object> fieldErrors) {
		this.httpStatus = httpStatus;
		this.localDateTime = localDateTime;
		this.message = message;
		var fields = new ArrayList<CauseError>();
		for (Object item : fieldErrors.toArray()) {
			if (item instanceof FieldError it) {
				fields.add(new CauseError(it.getField(), it.getDefaultMessage()));
			}
			else if (item instanceof JsonMappingException.Reference it) {
				fields.add(new CauseError(it.getFieldName(), it.getDescription()));
			}
			else if (item instanceof CauseError it) {
				fields.add(it);
			}
			else {
				fields.add(CauseError.builder().cause(item.toString()).build());
			}

		}
		this.detail = fields;

	}

}
