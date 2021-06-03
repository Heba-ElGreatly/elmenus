package com.elmenus.order.controller;

import com.elmenus.cart.dto.CartDTO;
import com.elmenus.order.dto.OrderDTO;
import com.elmenus.order.model.Orders;
import com.elmenus.order.service.CreatePaymentResponse;
import com.elmenus.order.service.OrderService;
import com.elmenus.order.util.CurrencyUtil;
import com.elmenus.order.util.OrderUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Autowired
    private OrderUtil orderUtil;

    @GetMapping("/get")
    ResponseEntity<List<OrderDTO>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/orderForm/{orderId}")
    public String orderForm(@PathVariable("orderId") Integer orderId, Model model) {
        CartDTO order = orderService.getUserPreOrderItems(orderId);
        model.addAttribute("order", order);

        return "orderForm";
    }

    @PostMapping("/orderForm")
    public String createOrder(CartDTO order, Model model) {
        CartDTO listToBeOrdered = orderService.getUserPreOrderItems(order.getOrderId());
        Stripe.apiKey = stripeSecretKey;
        Long totalAmount = orderUtil.calculateOrderAmountInPiasters(listToBeOrdered);
        model.addAttribute("totalAmount", Money.of(totalAmount, CurrencyUtil.EGP).divide(100).getNumberStripped());

        try {
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setCurrency("egp")
                    .setAmount(totalAmount)
                    .build();

            PaymentIntent intent = PaymentIntent.create(createParams);
            // da class fe string clientSecret el howa secret key bta3y
            CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());

            model.addAttribute("paymentResponse", paymentResponse);
            model.addAttribute("secretKey", stripeSecretKey);
        } catch (StripeException se) {
            se.printStackTrace();
        }
        return "payment";
    }

    @PutMapping("/updateOrder")
    ResponseEntity<Orders> placeOrder(@RequestBody OrderDTO orderDTO){
        Orders h = orderService.updateOrder(orderDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
