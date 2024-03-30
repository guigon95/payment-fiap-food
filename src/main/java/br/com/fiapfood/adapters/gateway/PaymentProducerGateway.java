package br.com.fiapfood.adapters.gateway;

import br.com.fiapfood.domain.model.Payment;

public interface PaymentProducerGateway {

	void publishMessage(Payment message);

}
