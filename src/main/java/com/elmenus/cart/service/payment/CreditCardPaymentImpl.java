package com.elmenus.cart.service.payment;


import com.elmenus.cart.dto.CartDTO;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

@Service
public class CreditCardPaymentImpl implements CreditCardPayment {


    @Override
    public String authorizePayment(CartDTO cartDTO) throws PayPalRESTException {
        PaymentServices paymentServices = new PaymentServices();
        String approvalLink = paymentServices.authorizePayment(cartDTO);
        return approvalLink;
    }

}
