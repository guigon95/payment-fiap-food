package br.com.fiapfood.external.api;

import br.com.fiapfood.adapters.controller.PaymentController;
import br.com.fiapfood.adapters.dto.request.PaymentRequest;
import br.com.fiapfood.adapters.dto.response.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "Access to payment management")
public class PaymentApi {

	private final PaymentController paymentController;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Search payment by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Payment found",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = PaymentResponse.class)) }),
			@ApiResponse(responseCode = "4xx", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "5xx", description = "Internal server error", content = @Content) })
	public ResponseEntity<PaymentResponse> paymentByID(@PathVariable(name = "id") @Schema(description = "id") Long id) {
		return paymentController.getPaymentById(id);
	}

	@GetMapping("/order/{order_id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Search payment by order_id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Payment found",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = PaymentResponse.class)) }),
			@ApiResponse(responseCode = "4xx", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "5xx", description = "Internal server error", content = @Content) })
	public ResponseEntity<PaymentResponse> paymentByOrderID(
			@PathVariable(name = "order_id") @Schema(description = "order id") Long orderID) {
		return paymentController.getPaymentByOrderId(orderID);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Create a payment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Payment Created",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = PaymentResponse.class)) }),
			@ApiResponse(responseCode = "4xx", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "5xx", description = "Internal server error", content = @Content) })
	public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
		return paymentController.createPayment(paymentRequest);
	}

	@PutMapping("/order/{order_id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Cancel a payment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Payment Cancelled",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = PaymentResponse.class)) }),
			@ApiResponse(responseCode = "4xx", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "5xx", description = "Internal server error", content = @Content) })
	public ResponseEntity<PaymentResponse> cancelPaymentByOrderId(
			@PathVariable("order_id") @NotNull(message = "The field orderId cannot be null.") Long orderId) {
		return paymentController.cancelPaymentByOrderId(orderId);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Cancel a payment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Payment Cancelled",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = PaymentResponse.class)) }),
			@ApiResponse(responseCode = "4xx", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "5xx", description = "Internal server error", content = @Content) })
	public ResponseEntity<PaymentResponse> cancelPayment(
			@PathVariable("id") @NotNull(message = "The field id cannot be null.") Long id) {
		return paymentController.cancelPayment(id);
	}

}
