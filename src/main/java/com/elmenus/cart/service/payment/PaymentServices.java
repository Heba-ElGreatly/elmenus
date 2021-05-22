package com.elmenus.cart.service.payment;

import com.elmenus.cart.dto.CartDTO;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServices {
    private static final String CLIENT_ID = "AabVEUItsJA9JlH83CEziItW1pEUwzykzqXvohV_ms5pFtwuUlIUJK3__QSzNPN1g_ZWlw5PVwGyNxxc";
    private static final String CLIENT_SECRET = "EJfWkyV6nAnwEnUEaqwzMF_zXEvTE9-R0midNvA8lAlL22J2MVdwkExo8-8ypceW79kUQWPv7Pd3ud5j";
    private static final String MODE = "sandbox";
    private static final String CURRENCY = "EGP";
    private static final Integer SHIPPING_AMOUNT = 50;
    private static final Integer TAX_AMOUNT = 30;

    public String authorizePayment(CartDTO cartDTO)
            throws PayPalRESTException {

        Payer payer = getPayerInformation(cartDTO);
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(cartDTO);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);
        return getApprovalLink(approvedPayment);
    }

    private Payer getPayerInformation(CartDTO cartDTO) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(cartDTO.getUserEmail());

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/PaypalTest/cancel.html");
        redirectUrls.setReturnUrl("http://localhost:8080/PaypalTest/review_payment");

        return redirectUrls;
    }

    private List<Transaction> getTransactionInformation(CartDTO cartDTO) {
        Details details = new Details();
        details.setSubtotal(String.valueOf(cartDTO.getSubTotal()));


        details.setShipping(String.valueOf(SHIPPING_AMOUNT));
        details.setTax(String.valueOf(TAX_AMOUNT));

        Amount amount = new Amount();
        amount.setCurrency(CURRENCY);
        amount.setTotal(String.valueOf(cartDTO.getTotal()));
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < cartDTO.getCartItems().size(); i++) {
            Item item = new Item();
            item.setCurrency(CURRENCY);
            item.setName(cartDTO.getCartItems().get(i).getItem().getItemName());
            item.setPrice(String.valueOf(cartDTO.getCartItems().get(i).getItem().getPrice()));
            item.setQuantity(String.valueOf(cartDTO.getCartItems().get(i).getItemQuantity()));


            item.setTax(String.valueOf(TAX_AMOUNT));

            items.add(item);
        }
        ;

        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);



        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId)
            throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        return payment.execute(apiContext, paymentExecution);
    }

}
