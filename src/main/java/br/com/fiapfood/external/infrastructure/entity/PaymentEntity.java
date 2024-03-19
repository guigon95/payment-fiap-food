package br.com.fiapfood.external.infrastructure.entity;

import br.com.fiapfood.domain.enums.CategoryCard;
import br.com.fiapfood.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
	private Long id;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "order_id")
	private Long orderId;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	@Column(name = "card_holder_name")
	private String cardHolderName;

	@Column(name = "card_expiration_date")
	private String cardExpirationDate;

	@Column(name = "card_cvv")
	private String cardCvv;

	@Column(name = "card_brand")
	private String cardBrand;

	@Enumerated(EnumType.STRING)
	private CategoryCard categoryCard;

	private String description;

	private BigDecimal amount;

}
