package br.com.fiapfood.external.gateway;

import br.com.fiapfood.adapters.gateway.PaymentExternalGateway;
import br.com.fiapfood.adapters.mapper.PaymentMapper;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.external.infrastructure.clients.gateway_card.ExternalGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentExternalGatewayImpl implements PaymentExternalGateway {

	private final ExternalGateway externalGateway;

	private final PaymentMapper paymentMapper;

	@Override
	public void sendPayment(Payment payment) {
		externalGateway.send(paymentMapper.paymentToPaymentGatewayDto(payment));
	}

}
