package br.com.fiapfood.adapters.controller.forms;

import br.com.fiapfood.adapters.dto.request.PaymentRequest;
import br.com.fiapfood.adapters.dto.response.PaymentResponse;
import br.com.fiapfood.adapters.mapper.PaymentMapper;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.domain.usecase.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class PaymentFormController {

    private final PaymentUseCase paymentUseCase;

    private final PaymentMapper paymentMapper;

    public ResponseEntity<Void> payment(Long id){
        paymentUseCase.createPayment(new Payment(id));
        return ResponseEntity.ok().build();
    }

    public PaymentResponse createPayment(PaymentRequest paymentRequest){
        Payment payment = paymentMapper.paymentRequestToPayment(paymentRequest);
        return paymentMapper.paymentToPaymentResponse(paymentUseCase.createPayment(payment));
    }

    public PaymentResponse cancelPayment(Long orderId){
        return paymentMapper.paymentToPaymentResponse(paymentUseCase.cancelPayment(orderId));
    }
}
