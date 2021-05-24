package com.elmenus.cart.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CartDTO {

    private Integer orderId;
    private String userEmail;
    private List<CartItemDTO> cartItems;
    private double subTotal;
    private double tax;
    private double total;

}
