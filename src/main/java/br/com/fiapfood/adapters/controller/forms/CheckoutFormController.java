package br.com.fiapfood.adapters.controller.forms;

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
public class CheckoutFormController {

    private final CheckoutUseCase checkoutUseCase;
    private final CheckoutMapper checkoutMapper;
    public CheckoutResponse createCheckout(Long orderId,  BigDecimal amount) {
        return checkoutMapper.checkoutToCheckoutResponse(checkoutUseCase.createCheckout(new Checkout(orderId, amount)));
    }
    public CheckoutResponse getCheckout(Long orderId) {
        return checkoutMapper.checkoutToCheckoutResponse(checkoutUseCase.getCheckout(orderId));
    }

    public String getQrCodeCheckout(Long orderId, BigDecimal amount) {
        return checkoutUseCase.getQrCodeCheckout(orderId, amount);
    }
}
