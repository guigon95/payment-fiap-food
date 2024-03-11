package br.com.fiapfood.external.infrastructure.repository;

import br.com.fiapfood.adapters.gateway.PaymentGateway;
import br.com.fiapfood.adapters.mapper.PaymentMapper;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.external.infrastructure.entity.PaymentEntity;
import br.com.fiapfood.external.infrastructure.repository.jpa.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

	private final PaymentRepository paymentRepository;

	private final PaymentMapper paymentMapper;

	@Override
	public Payment save(Payment payment) {
		var paymentEntity = paymentMapper.paymentToPaymentEntity(payment);
		return paymentMapper.paymentEntityToPayment(paymentRepository.save(paymentEntity));
	}

	@Override
	public Payment findByOrderID(Long orderId) {
		PaymentEntity paymentEntity = paymentRepository.findByOrderId(orderId).orElse(null);
		return paymentMapper.paymentEntityToPayment(paymentEntity);
	}

	@Override
	public Payment findByID(Long id) {
		PaymentEntity paymentEntity = paymentRepository.findById(id).orElse(null);
		return paymentMapper.paymentEntityToPayment(paymentEntity);
	}

}
