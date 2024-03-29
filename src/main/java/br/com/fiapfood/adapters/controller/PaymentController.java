package br.com.fiapfood.adapters.controller;

import br.com.fiapfood.adapters.dto.request.PaymentRequest;
import br.com.fiapfood.adapters.dto.response.PaymentResponse;
import br.com.fiapfood.adapters.mapper.PaymentMapper;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.domain.usecase.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentUseCase paymentUseCase;

	private final PaymentMapper paymentMapper;

	public ResponseEntity<PaymentResponse> createPayment(PaymentRequest paymentRequest) {
		Payment payment = paymentMapper.paymentRequestToPayment(paymentRequest);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(paymentMapper.paymentToPaymentResponse(paymentUseCase.createPayment(payment)));
	}

	public ResponseEntity<PaymentResponse> cancelPaymentByOrderId(Long orderId) {
		return ResponseEntity
			.ok(paymentMapper.paymentToPaymentResponse(paymentUseCase.cancelPaymentByOrderId(orderId)));
	}

	public ResponseEntity<PaymentResponse> cancelPayment(Long orderId) {
		return ResponseEntity.ok(paymentMapper.paymentToPaymentResponse(paymentUseCase.cancelPayment(orderId)));
	}

	public ResponseEntity<PaymentResponse> getPaymentById(Long id) {
		return ResponseEntity.ok(paymentMapper.paymentToPaymentResponse(paymentUseCase.getPaymentById(id)));
	}

	public ResponseEntity<PaymentResponse> getPaymentByOrderId(Long orderId) {
		return ResponseEntity.ok(paymentMapper.paymentToPaymentResponse(paymentUseCase.getPaymentByOrderId(orderId)));
	}

}
