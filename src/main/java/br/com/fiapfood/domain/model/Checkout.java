package br.com.fiapfood.domain.model;

import br.com.fiapfood.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Checkout {

    private Long ordem;
    private Long orderId;
    private String qrCode;
    private BigDecimal amount;

    private PaymentStatus paymentStatus;
    public Checkout(Long orderId,  BigDecimal amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public Boolean exist() {
        return this.orderId != null;
    }
}
