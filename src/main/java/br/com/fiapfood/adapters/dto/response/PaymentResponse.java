package br.com.fiapfood.adapters.dto.response;

import br.com.fiapfood.domain.enums.CategoryCard;
import br.com.fiapfood.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class PaymentResponse {

	private Long id;

	@JsonProperty("order_id")
	private Long orderId;

	private PaymentStatus status;

	private String description;

	private BigDecimal amount;

	@JsonProperty("category_card")
	private CategoryCard categoryCard;

	@JsonIgnore
	private String cardNumber;

	@JsonProperty("card_number")
	@ToString.Include(name = "card_number")
	public String getMaskedCardNumber() {
		String maskCard = cardNumber.substring(0, 4) + "********" + cardNumber.substring(cardNumber.length() - 4);
		cardNumber = "";
		return maskCard;
	}

}
