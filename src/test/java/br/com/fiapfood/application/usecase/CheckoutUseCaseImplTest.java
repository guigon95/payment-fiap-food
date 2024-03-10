package br.com.fiapfood.application.usecase;

import br.com.fiapfood.PaymentApplicationTest;
import br.com.fiapfood.adapters.gateway.CheckoutGateway;
import br.com.fiapfood.domain.model.Checkout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PaymentApplicationTest.class)
class CheckoutUseCaseImplTest {
    @Mock
    private CheckoutGateway checkoutGateway;

    @InjectMocks
    private CheckoutUseCaseImpl checkoutUseCaseImpl;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCheckout_Success() {
        Checkout checkout = Checkout.builder().orderId(5L).build();
        when(checkoutGateway.findByOrderId(checkout.getOrderId())).
                thenReturn( Checkout.builder().build());
        when(checkoutGateway.save(checkout)).thenReturn(checkout);

        Checkout returnCheckout = checkoutUseCaseImpl.createCheckout(checkout);

        assertEquals(checkout , returnCheckout);

    }

    @Test
    void createCheckout_Exists() {
        Checkout checkout = Checkout.builder().orderId(5L).build();
        Checkout checkoutOld = Checkout.builder().orderId(5L).ordem(9L).build();
        when(checkoutGateway.findByOrderId(checkout.getOrderId())).
                thenReturn(checkoutOld);
//        when(checkoutGateway.save(checkout)).thenReturn(checkout);

        Checkout returnCheckout = checkoutUseCaseImpl.createCheckout(checkout);

        assertEquals(checkoutOld , returnCheckout);

    }
}