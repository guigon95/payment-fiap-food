package br.com.fiapfood.adapters.controller;

import br.com.fiapfood.adapters.dto.response.CheckoutResponse;
import br.com.fiapfood.adapters.mapper.CheckoutMapper;
import br.com.fiapfood.domain.model.Checkout;
import br.com.fiapfood.domain.usecase.CheckoutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

	private final CheckoutUseCase checkoutUseCase;

	private final CheckoutMapper checkoutMapper;

	public ResponseEntity<CheckoutResponse> checkout(Long orderId, BigDecimal amount) {
		return ResponseEntity.ok(checkoutMapper
			.checkoutToCheckoutResponse(checkoutUseCase.createCheckout(new Checkout(orderId, amount))));
	}

	public ResponseEntity<CheckoutResponse> getCheckout(Long orderId) {
		return ResponseEntity.ok(checkoutMapper.checkoutToCheckoutResponse(checkoutUseCase.getCheckout(orderId)));
	}

}
