package br.com.fiapfood.adapters.gateway;

import br.com.fiapfood.domain.model.Payment;

public interface PaymentGateway {

	Payment save(Payment payment);

	Payment findByOrderID(Long orderId);

	Payment findByID(Long id);

}
