package com.elmenus.cart.service.payment;

import com.elmenus.cart.dto.CartDTO;
import com.paypal.base.rest.PayPalRESTException;


public interface CreditCardPayment {

    String authorizePayment(CartDTO cartDTO) throws PayPalRESTException;
}
