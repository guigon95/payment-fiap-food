package br.com.fiapfood.external.forms;

import br.com.fiapfood.adapters.controller.forms.PaymentFormController;
import br.com.fiapfood.adapters.dto.request.PaymentRequest;
import br.com.fiapfood.adapters.dto.response.PaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/form/payment")
@RequiredArgsConstructor
public class PaymentForm {

    private final PaymentFormController paymentController;

    @PostMapping("")
    public String createPayment(@Valid PaymentRequest paymentRequest, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            return "checkout";
        }
        PaymentResponse payment = paymentController.createPayment(paymentRequest);

        if (payment == null) {
            return "error";
        }
        model.addAttribute("orderId", paymentRequest.getOrderId());
        model.addAttribute("amount", paymentRequest.getAmount());

        return "success";
    }

}
