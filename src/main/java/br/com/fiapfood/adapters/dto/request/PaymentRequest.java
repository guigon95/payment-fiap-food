package br.com.fiapfood.adapters.dto.request;

import br.com.fiapfood.adapters.dto.request.validations.CardExpiration;
import br.com.fiapfood.domain.enums.CategoryCard;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Builder
public class PaymentRequest {

	@NotEmpty(message = "The field cardNumber cannot be empty.")
	@Schema(description = "number that identifies the card", example = "1234567891234567")
	@CreditCardNumber(message = "The value is not a valid credit card number.")
	@JsonProperty("card_number")
	private String cardNumber;

	@NotNull(message = "The field orderId cannot be empty.")
	@Schema(description = "identifies the order", example = "443")
	@JsonProperty("order_id")
	private Long orderId;

	@NotEmpty(message = "The field card_holder_name cannot be empty.")
	@Schema(description = "name of the card holder")
	@JsonProperty("card_holder_name")
	private String cardHolderName;

	@NotEmpty(message = "The field card_expiration_date cannot be empty.")
	@Schema(description = "card expiration date", example = "12/2025")
	@CardExpiration(message = "The field cardExpirationDate is not a valid expiration date.")
	@JsonProperty("card_expiration_date")
	private String cardExpirationDate;

	@NotEmpty(message = "The field card_cvv cannot be empty.")
	@Schema(description = "card security code", example = "123")
	@JsonProperty("card_cvv")
	private String cardCvv;

	@NotEmpty(message = "The field card_brand cannot be empty.")
	@Schema(description = "card brand", example = "VISA")
	@JsonProperty("card_brand")
	private String cardBrand;

	// @NotEmpty(message = "The field description cannot be empty.")
	@Schema(description = "short description about the payment")
	private String description;

	@NotNull(message = "The field amount cannot be empty.")
	@Schema(description = "amount of the payment", example = "100.00")
	private BigDecimal amount;

	@NotNull(message = "The field category cannot be empty.")
	@Enumerated(EnumType.STRING)
	@Schema(description = "category card type", defaultValue = "CREDIT_CARD", example = "CREDIT_CARD")
	@JsonProperty("category_card")
	private CategoryCard categoryCard;

}
