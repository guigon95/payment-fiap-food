package br.com.fiapfood.adapters.dto.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class PaymentMessageDto {

	@JsonProperty("order_id")
	Long orderId;

}
