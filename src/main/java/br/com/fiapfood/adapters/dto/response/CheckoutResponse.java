package br.com.fiapfood.adapters.dto.response;

import br.com.fiapfood.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CheckoutResponse {

	@JsonProperty("order_id")
	private Long orderId;

	@JsonProperty("amount")
	private String amount;

	@JsonProperty("qr_code")
	private String qrCode;

	@JsonProperty("payment_status")
	private PaymentStatus paymentStatus;

}
