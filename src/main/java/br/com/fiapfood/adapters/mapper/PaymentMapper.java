package br.com.fiapfood.adapters.mapper;

import br.com.fiapfood.adapters.dto.messaging.PaymentGatewayDto;
import br.com.fiapfood.adapters.dto.request.PaymentRequest;
import br.com.fiapfood.adapters.dto.request.PaymentStatusRequest;
import br.com.fiapfood.adapters.dto.response.PaymentResponse;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.external.infrastructure.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

	Payment paymentEntityToPayment(PaymentEntity paymentEntity);

	@Mapping(target = "cardNumber", qualifiedByName = "maskCardNumber")
	PaymentEntity paymentToPaymentEntity(Payment payment);

	Payment paymentRequestToPayment(PaymentRequest paymentRequest);

	PaymentResponse paymentToPaymentResponse(Payment payment);

	Payment paymentStatusRequestToPayment(PaymentStatusRequest paymentStatusRequest);

	PaymentGatewayDto paymentToPaymentGatewayDto(Payment payment);

	@Named("maskCardNumber")
	static String maskCardNumber(String cardNumber) {
		return cardNumber.substring(0, 4) + "********" + cardNumber.substring(cardNumber.length() - 4);
	}

}
