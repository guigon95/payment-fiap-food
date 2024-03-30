package br.com.fiapfood.external.api;

import br.com.fiapfood.adapters.controller.CheckoutController;
import br.com.fiapfood.adapters.dto.response.CheckoutResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.RequiredArgsConstructor;
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
			@Valid @RequestParam("amount") @Schema(description = "amount of the payment") @DecimalMin(value = "0.01",
					inclusive = false, message = "Invalid amount, please amount more zero") @Digits(integer = 100,
							fraction = 2) BigDecimal amount) {
		return checkoutController.checkout(id, amount);
	}

	@GetMapping("/order/{order_id}")
	public ResponseEntity<CheckoutResponse> getCheckout(
			@PathVariable(name = "order_id") @Schema(description = "order id") Long id) {
		return checkoutController.getCheckout(id);
	}

}
