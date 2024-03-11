package br.com.fiapfood.application.usecase;

import br.com.fiapfood.adapters.gateway.CheckoutGateway;
import br.com.fiapfood.adapters.gateway.PaymentGateway;
import br.com.fiapfood.adapters.gateway.QrCodeGateway;
import br.com.fiapfood.application.exception.ObjectException;
import br.com.fiapfood.application.exception.ObjectNotFoundException;
import br.com.fiapfood.domain.model.Checkout;
import br.com.fiapfood.domain.model.Payment;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CheckoutUseCaseImplTest {

	@Mock
	private CheckoutGateway checkoutGateway;

	@Mock
	private PaymentGateway paymentGateway;

	@Mock
	private QrCodeGateway qrCodeGateway;

	@InjectMocks
	private CheckoutUseCaseImpl checkoutUseCaseImpl;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void createCheckout_Success() throws IOException, WriterException {
		Checkout checkout = Checkout.builder().orderId(5L).amount(BigDecimal.valueOf(23.43)).build();
		when(checkoutGateway.findByOrderId(checkout.getOrderId())).thenReturn(null);
		byte[] qrCode = new byte[0];
		when(qrCodeGateway.getQRCodeImage(anyString(), anyInt(), anyInt())).thenReturn(qrCode);

		when(checkoutGateway.save(checkout)).thenReturn(checkout);

		Checkout result = checkoutUseCaseImpl.createCheckout(checkout);

		assertEquals(checkout, result);
		verify(checkoutGateway).findByOrderId(5L);
		verify(checkoutGateway).save(checkout);
		verify(qrCodeGateway).getQRCodeImage(anyString(), anyInt(), anyInt());

	}

	@Test
	void createCheckout_Exists() {
		Checkout checkout = Checkout.builder().orderId(5L).build();
		Checkout checkoutOld = Checkout.builder().orderId(5L).ordem(9L).build();
		when(checkoutGateway.findByOrderId(checkout.getOrderId())).thenReturn(checkoutOld);

		Checkout result = checkoutUseCaseImpl.createCheckout(checkout);

		assertEquals(checkoutOld, result);
		verify(checkoutGateway).findByOrderId(5L);
	}

	@Test
	void getCheckout_Exists_PaymentExists() {
		Long orderId = 20L;
		Checkout checkout = Checkout.builder().orderId(orderId).build();
		Payment payment = Payment.builder().id(63L).orderId(orderId).build();
		when(checkoutGateway.findByOrderId(orderId)).thenReturn(checkout);
		when(paymentGateway.findByOrderID(orderId)).thenReturn(payment);

		Checkout result = checkoutUseCaseImpl.getCheckout(orderId);

		assertEquals(checkout, result);
		assertEquals(payment.getStatus(), result.getPaymentStatus());
		verify(checkoutGateway).findByOrderId(orderId);
		verify(paymentGateway).findByOrderID(orderId);
	}

	@Test
	void getCheckout_Exists_PaymentNotExists() {
		Long orderId = 5L;
		Checkout checkout = Checkout.builder().orderId(orderId).build();
		when(checkoutGateway.findByOrderId(orderId)).thenReturn(checkout);
		when(paymentGateway.findByOrderID(orderId)).thenReturn(null);

		Checkout result = checkoutUseCaseImpl.getCheckout(orderId);

		assertEquals(checkout, result);
		verify(checkoutGateway).findByOrderId(orderId);
		verify(paymentGateway).findByOrderID(orderId);
	}

	@Test
	void getCheckout_NotExists() {
		Long orderId = 5L;
		when(checkoutGateway.findByOrderId(orderId)).thenReturn(null);

		assertThrows(ObjectNotFoundException.class, () -> checkoutUseCaseImpl.getCheckout(orderId));
		verify(checkoutGateway).findByOrderId(orderId);
	}

	@Test
	void getQrCodeCheckout_Success() throws IOException, WriterException {
		Long orderId = 5L;
		BigDecimal amount = BigDecimal.valueOf(100);
		byte[] qrCode = new byte[0];
		when(qrCodeGateway.getQRCodeImage(anyString(), anyInt(), anyInt())).thenReturn(qrCode);

		String result = checkoutUseCaseImpl.getQrCodeCheckout(orderId, amount);

		assertNotNull(result);
	}

	@Test
	void getQrCodeCheckout_Error() throws IOException, WriterException {
		// Arrange
		Long orderId = 5L;
		BigDecimal amount = BigDecimal.valueOf(100);
		when(qrCodeGateway.getQRCodeImage(anyString(), anyInt(), anyInt())).thenThrow(IOException.class);

		// Act & Assert
		assertThrows(ObjectException.class, () -> checkoutUseCaseImpl.getQrCodeCheckout(orderId, amount));
	}

}