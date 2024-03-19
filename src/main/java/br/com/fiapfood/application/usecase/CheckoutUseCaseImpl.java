package br.com.fiapfood.application.usecase;

import br.com.fiapfood.adapters.gateway.CheckoutGateway;
import br.com.fiapfood.adapters.gateway.PaymentGateway;
import br.com.fiapfood.adapters.gateway.QrCodeGateway;
import br.com.fiapfood.application.exception.ObjectException;
import br.com.fiapfood.application.exception.ObjectNotFoundException;
import br.com.fiapfood.domain.model.Checkout;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.domain.usecase.CheckoutUseCase;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class CheckoutUseCaseImpl implements CheckoutUseCase {

	private final CheckoutGateway checkoutGateway;

	private final PaymentGateway paymentGateway;

	private final QrCodeGateway qrCodeGateway;

	@Override
	public Checkout createCheckout(Checkout checkout) {

		Checkout checkoutOld = checkoutGateway.findByOrderId(checkout.getOrderId());
		if (checkoutOld != null)
			return checkoutOld;

		checkout.setQrCode(getQrCodeCheckout(checkout.getOrderId(), checkout.getAmount()));
		return checkoutGateway.save(checkout);
	}

	@Override
	public Checkout getCheckout(Long orderId) {
		Checkout checkout = checkoutGateway.findByOrderId(orderId);
		if (checkout == null)
			throw new ObjectNotFoundException("Checkout not found");

		Payment payment = paymentGateway.findByOrderID(checkout.getOrderId());
		if (payment != null) {
			checkout.setPaymentStatus(payment.getStatus());
		}

		return checkout;
	}

	@Override
	public String getQrCodeCheckout(Long orderId, BigDecimal amount) {
		String url = "http://localhost:8080/form/checkout/order/order/" + orderId + "?amount=" + amount;
		byte[] image;
		try {
			image = qrCodeGateway.getQRCodeImage(url, 350, 350);
		}
		catch (WriterException | IOException e) {
			throw new ObjectException("Error while generating QR code");
		}
		return Base64.getEncoder().encodeToString(image);
	}

}
