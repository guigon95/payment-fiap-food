package br.com.fiapfood.external.gateway;

import br.com.fiapfood.adapters.dto.messaging.PaymentMessageDto;
import br.com.fiapfood.adapters.gateway.PaymentProducerGateway;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.external.infrastructure.clients.messaging.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentProducerGatewayImpl implements PaymentProducerGateway {

	private final PaymentProducer paymentProducer;

	@Override
	public void publishMessage(Payment payment) {
		paymentProducer.publishMessage(PaymentMessageDto.builder().orderId(payment.getId()).build());
	}

}
