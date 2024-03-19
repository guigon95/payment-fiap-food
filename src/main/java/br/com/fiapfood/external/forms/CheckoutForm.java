package br.com.fiapfood.external.forms;

import br.com.fiapfood.adapters.controller.forms.CheckoutFormController;
import br.com.fiapfood.adapters.dto.request.PaymentRequest;
import br.com.fiapfood.adapters.dto.response.CheckoutResponse;
import br.com.fiapfood.application.exception.ObjectNotFoundException;
import br.com.fiapfood.domain.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Objects;

@Controller
@RequestMapping("/form/checkout")
@RequiredArgsConstructor
public class CheckoutForm {

	public static final String ORDER_ID = "orderId";

	private final CheckoutFormController checkoutController;

	@GetMapping("/qr-code/order/{order_id}")
	public String showQrCodeByOrderIdForm(@PathVariable(name = "order_id") @Schema(description = "order id") Long id,
			Model model) {

		try {
			CheckoutResponse checkout = checkoutController.getCheckout(id);
			model.addAttribute("encodedImage", checkout.getQrCode());
		}
		catch (ObjectNotFoundException e) {
			model.addAttribute(ORDER_ID, id);
			return "not_found";
		}

		return "qrcode_checkout";
	}

	@GetMapping("/gen/qr-code/order/{order_id}")
	public String generatedQrCodeByOrderIdForm(
			@NotNull @PathVariable(name = "order_id") @Schema(description = "order id") Long id,
			@NotNull @Param("amount") @Schema(description = "amount of the payment") BigDecimal amount, Model model) {

		try {
			CheckoutResponse checkout = checkoutController.getCheckout(id);
			if (!Objects.isNull(checkout)) {
				return "invalid_operation";
			}
		}
		catch (ObjectNotFoundException e) {
			model.addAttribute("encodedImage", checkoutController.getQrCodeCheckout(id, amount));
		}

		return "qrcode_checkout";
	}

	@GetMapping("/order/{order_id}")
	public String createCheckoutForm(@PathVariable(name = "order_id") @Schema(description = "order id") Long id,
			@Param("amount") @Schema(description = "amount of the payment") BigDecimal amount, Model model) {

		try {
			CheckoutResponse checkout = checkoutController.createCheckout(id, amount);
			if (PaymentStatus.APPROVED.equals(checkout.getPaymentStatus())) {
				model.addAttribute(ORDER_ID, id);
				model.addAttribute("paymentStatus", checkout.getPaymentStatus().toString());
				return "payment_status";
			}
		}
		catch (ObjectNotFoundException e) {
			model.addAttribute(ORDER_ID, id);
			return "not_found";
		}

		model.addAttribute("paymentRequest", PaymentRequest.builder().orderId(id).amount(amount).build());
		return "checkout";
	}

}
