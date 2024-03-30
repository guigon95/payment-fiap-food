package br.com.fiapfood.application.usecase;

import br.com.fiapfood.adapters.gateway.PaymentGateway;
import br.com.fiapfood.adapters.gateway.PaymentProducerGateway;
import br.com.fiapfood.application.exception.InvalidStatusException;
import br.com.fiapfood.application.exception.ObjectNotFoundException;
import br.com.fiapfood.domain.enums.PaymentStatus;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.domain.usecase.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentUseCaseImpl implements PaymentUseCase {

	private final PaymentGateway paymentGateway;

	private final PaymentProducerGateway paymentProducerGateway;

	@Override
	public Payment createPayment(Payment payment) {

		Payment paymentOld = paymentGateway.findByOrderID(payment.getOrderId());
		if (paymentOld != null)
			return paymentOld;

		payment.setStatus(PaymentStatus.PROCESSING);
		return paymentGateway.save(payment);
	}

	@Override
	public Payment cancelPaymentByOrderId(Long orderId) {
		Payment payment = paymentGateway.findByOrderID(orderId);
		if (payment == null)
			throw new ObjectNotFoundException("Payment not exists");

		payment.setStatus(PaymentStatus.CANCELED);
		return paymentGateway.save(payment);
	}

	@Override
	public Payment cancelPayment(Long id) {
		Payment payment = paymentGateway.findByID(id);
		if (payment == null)
			throw new ObjectNotFoundException("Payment not exists");

		payment.setStatus(PaymentStatus.CANCELED);
		return paymentGateway.save(payment);
	}

	@Override
	public Payment getPaymentByOrderId(Long orderId) {
		Payment payment = paymentGateway.findByOrderID(orderId);
		if (payment == null)
			throw new ObjectNotFoundException("Payment not found");

		return payment;
	}

	@Override
	public Payment getPaymentById(Long id) {
		Payment payment = paymentGateway.findByID(id);
		if (payment == null)
			throw new ObjectNotFoundException("Payment not found");

		return payment;
	}

	@Override
	public void updateStatusByID(Payment payment) {
		Payment paymentNew = getPaymentById(payment.getId());
		if (!paymentNew.isValidStatus(payment.getStatus()))
			throw new InvalidStatusException("Invalid status, cannot return to previous status [new status: "
					+ payment.getStatus() + "][old status: " + paymentNew.getStatus() + "]");

		paymentNew.setStatus(payment.getStatus());
		paymentGateway.save(paymentNew);
		if (paymentNew.getStatus().equals(PaymentStatus.APPROVED))
			paymentProducerGateway.publishMessage(paymentNew);
	}

}
