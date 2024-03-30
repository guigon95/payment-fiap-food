package br.com.fiapfood.adapters.gateway;

import br.com.fiapfood.domain.model.Payment;

public interface PaymentExternalGateway {

	void sendPayment(Payment message);

}
