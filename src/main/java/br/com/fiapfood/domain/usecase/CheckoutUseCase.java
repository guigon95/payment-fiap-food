package br.com.fiapfood.domain.usecase;

import br.com.fiapfood.domain.model.Checkout;

import java.math.BigDecimal;

public interface CheckoutUseCase {
    Checkout createCheckout(Checkout checkout);
    Checkout getCheckout(Long orderId) ;
    String getQrCodeCheckout(Long orderId, BigDecimal amount) ;
}
