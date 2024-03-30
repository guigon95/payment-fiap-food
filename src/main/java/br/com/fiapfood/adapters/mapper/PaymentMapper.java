package br.com.fiapfood.adapters.mapper;

import br.com.fiapfood.adapters.dto.request.PaymentRequest;
import br.com.fiapfood.adapters.dto.request.PaymentStatusRequest;
import br.com.fiapfood.adapters.dto.response.PaymentResponse;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.external.infrastructure.entity.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

	Payment paymentEntityToPayment(PaymentEntity paymentEntity);

	PaymentEntity paymentToPaymentEntity(Payment payment);

	Payment paymentRequestToPayment(PaymentRequest paymentRequest);

	PaymentResponse paymentToPaymentResponse(Payment payment);

	Payment paymentStatusRequestToPayment(PaymentStatusRequest paymentStatusRequest);

}
