package br.com.fiapfood.adapters.gateway;

import br.com.fiapfood.domain.model.Checkout;

public interface CheckoutGateway  {
    Checkout save(Checkout checkout);
    Checkout findByOrderId(Long id);
}

