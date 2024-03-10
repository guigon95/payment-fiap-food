package br.com.fiapfood.application.usecase;

import br.com.fiapfood.adapters.gateway.PaymentGateway;
import br.com.fiapfood.application.exception.ObjectNotFoundException;
import br.com.fiapfood.domain.enums.PaymentStatus;
import br.com.fiapfood.domain.model.Payment;
import br.com.fiapfood.domain.usecase.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentUseCaseImpl implements PaymentUseCase {

    private final PaymentGateway paymentGateway;


    @Override
    public Payment createPayment(Payment payment) {

        Payment paymentOld = paymentGateway.findByOrderID(payment.getOrderId());
        if(Boolean.TRUE.equals(paymentOld.Exist()))
            return paymentOld;

        payment.setStatus(PaymentStatus.APPROVED);
        return paymentGateway.save(payment);
    }

    @Override
    public Payment cancelPaymentByOrderId(Long orderId){
        Payment payment = paymentGateway.findByOrderID(orderId);
        if(Boolean.FALSE.equals(payment.Exist()))
            throw new ObjectNotFoundException("Payment not exists");

        payment.setStatus(PaymentStatus.CANCELED);
        return paymentGateway.save(payment);
    }

    @Override
    public Payment cancelPayment(Long id){
        Payment payment = paymentGateway.findByID(id);
        if(Boolean.FALSE.equals(payment.Exist()))
            throw new ObjectNotFoundException("Payment not exists");

        payment.setStatus(PaymentStatus.CANCELED);
        return paymentGateway.save(payment);
    }

    @Override
    public Payment getPaymentByOrderId(Long orderId) {
        Payment payment = paymentGateway.findByOrderID(orderId);
        if(Boolean.FALSE.equals(payment.Exist()))
            throw new ObjectNotFoundException("Payment not found");

        return payment;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment payment = paymentGateway.findByID(id);
        if(Boolean.FALSE.equals(payment.Exist()))
            throw new ObjectNotFoundException("Payment not found");

        return payment;
    }


}
