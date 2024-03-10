package br.com.fiapfood.adapters.mapper;

import br.com.fiapfood.adapters.dto.response.CheckoutResponse;
import br.com.fiapfood.domain.model.Checkout;
import br.com.fiapfood.external.infrastructure.entity.CheckoutEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckoutMapper {

    Checkout checkoutEntityToCheckout(CheckoutEntity checkoutEntity);
    CheckoutResponse checkoutToCheckoutResponse(Checkout checkout);

    CheckoutEntity checkouToCheckoutEntity(Checkout checkout);


}
