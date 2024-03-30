package br.com.fiapfood.adapters.dto.messaging;

import br.com.fiapfood.domain.enums.CategoryCard;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentGatewayDto {

	@JsonProperty("card_number")
	private String cardNumber;

	@JsonProperty("card_holder_name")
	private String cardHolderName;

	@JsonProperty("card_expiration_date")
	private String cardExpirationDate;

	@JsonProperty("card_cvv")
	private String cardCvv;

	@JsonProperty("card_brand")
	private String cardBrand;

	private String description;

	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	@JsonProperty("category_card")
	private CategoryCard categoryCard;

}
