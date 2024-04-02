package br.com.fiapfood.external.infrastructure.entity;

import br.com.fiapfood.domain.enums.CategoryCard;
import br.com.fiapfood.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payment", uniqueConstraints = @UniqueConstraint(columnNames = { "order_id" }))
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
	private Long id;

	@Column(name = "order_id")
	private Long orderId;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	@Enumerated(EnumType.STRING)
	private CategoryCard categoryCard;

	@JsonProperty("card_number")
	private String cardNumber;

	private String description;

	private BigDecimal amount;

}
