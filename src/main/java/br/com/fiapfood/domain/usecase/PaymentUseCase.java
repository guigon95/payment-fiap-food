package br.com.fiapfood.domain.usecase;

import br.com.fiapfood.domain.model.Payment;

public interface PaymentUseCase {
    Payment createPayment(Payment payment);

    Payment cancelPaymentByOrderId(Long orderId);
    Payment cancelPayment(Long id);

    Payment getPaymentByOrderId(Long orderId);
    Payment getPaymentById(Long id);
}
