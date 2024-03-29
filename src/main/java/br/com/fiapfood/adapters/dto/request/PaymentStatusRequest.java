package br.com.fiapfood.adapters.dto.request;

import br.com.fiapfood.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PaymentStatusRequest {

	@NotNull(message = "The field id cannot be null.")
	@Schema(description = "id of the payment")
	@JsonProperty("id")
	private Long id;

	@NotNull(message = "The field status cannot be empty.")
	@Enumerated(EnumType.STRING)
	@Schema(description = "payment status", defaultValue = "PROCESSING")
	@JsonProperty("status")
	private PaymentStatus status;

}
