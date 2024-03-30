package br.com.fiapfood.external.infrastructure.entity;

import br.com.fiapfood.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "checkout", uniqueConstraints = @UniqueConstraint(columnNames = { "order_id" }))
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CheckoutEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ordem", nullable = false, columnDefinition = "BIGINT")
	private Long ordem;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "qr_code")
	@Lob
	private String qrCode;

	@Column(name = "amount")
	private BigDecimal amount;

	@Transient
	private PaymentStatus paymentStatus;

}
