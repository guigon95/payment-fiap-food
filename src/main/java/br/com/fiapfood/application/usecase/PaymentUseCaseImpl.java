package br.com.fiapfood.application.usecase;

import br.com.fiapfood.adapters.dto.response.exceptions.ErrorMessage;
import br.com.fiapfood.adapters.gateway.PaymentGateway;
import br.com.fiapfood.adapters.mapper.PaymentMapper;
import br.com.fiapfood.application.exception.InvalidFieldException;
import br.com.fiapfood.application.exception.ObjectNotFoundException;
import br.com.fiapfood.domain.enums.PaymentStatus;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.domain.usecase.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentUseCaseImpl implements PaymentUseCase {

	private final PaymentGateway paymentGateway;

	private final PaymentMapper paymentMapper;

	@Override
	public Payment createPayment(Payment payment) {

		Payment paymentOld = paymentGateway.findByOrderID(payment.getOrderId());
		if (paymentOld != null)
			return paymentOld;

		payment.setStatus(PaymentStatus.APPROVED);
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
		if (Boolean.FALSE.equals(payment.exist()))
			throw new ObjectNotFoundException("Payment not found");

		return payment;
	}

	@Override
	public Payment getPaymentById(Long id) {
		Payment payment = paymentGateway.findByID(id);
		if (Boolean.FALSE.equals(payment.exist()))
			throw new ObjectNotFoundException("Payment not found");

		return payment;
	}

	@Override
	public void updateStatusByID(Payment payment) {
		Payment paymentNew = paymentGateway.findByID(payment.getId());
		if (paymentNew.isValidStatus(payment.getStatus()))
			throw new IllegalStateException("Invalid status");
		paymentNew.setStatus(payment.getStatus());
	}

}
