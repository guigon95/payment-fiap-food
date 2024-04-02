package br.com.fiapfood.adapters.dto.response;

import br.com.fiapfood.domain.enums.CategoryCard;
import br.com.fiapfood.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentResponse {

	private Long id;

	@JsonProperty("order_id")
	private Long orderId;

	private PaymentStatus status;

	private String description;

	private BigDecimal amount;

	@JsonProperty("category_card")
	private CategoryCard categoryCard;

	@JsonProperty("card_number")
	private String cardNumber;

}
