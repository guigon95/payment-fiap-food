package br.com.fiapfood.external.api;

import br.com.fiapfood.adapters.controller.CheckoutController;
import br.com.fiapfood.adapters.dto.response.CheckoutResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
@Tag(name = "Checkout", description = "Access to checkout management")
public class CheckoutApi {

	private final CheckoutController checkoutController;

	@PostMapping("/order/{order_id}")
	public ResponseEntity<CheckoutResponse> checkout(
			@Valid @PathVariable(name = "order_id") @Schema(description = "order id") Long id,
			@Param("amount") @Schema(description = "amount of the payment") BigDecimal amount) {
		return checkoutController.checkout(id, amount);
	}

	@GetMapping("/order/{order_id}")
	public ResponseEntity<CheckoutResponse> getCheckout(
			@PathVariable(name = "order_id") @Schema(description = "order id") Long id) {
		return checkoutController.getCheckout(id);
	}

}
