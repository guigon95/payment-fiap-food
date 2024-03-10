package br.com.fiapfood.domain.model;

import br.com.fiapfood.domain.enums.CategoryCard;
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
public class Payment {

    private Long id;
    private String cardNumber;
    private Long orderId;
    private PaymentStatus status;
    private String cardHolderName;
    private String cardExpirationDate;
    private String cardCvv;
    private String cardBrand;
    private String description;
    private BigDecimal amount;
    private CategoryCard categoryCard;

    public Payment(Long orderId) {
        this.orderId = orderId;
    }

    public Boolean Exist() {
        return this.id > 0 && this.orderId > 0;
    }
}
