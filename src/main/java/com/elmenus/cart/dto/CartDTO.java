package com.elmenus.cart.dto;


import com.elmenus.user.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDTO {

    private String userEmail;
    private List<CartItemDTO> cartItems;
    private double subTotal;
    private double total;

//    public String getSubtotal() {
//        return String.format("%.2f", subTotal);
//    }
//
//    public String getShipping() {
//        return String.format("%.2f", shipping);
//    }
//
//    public String getTax() {
//        return String.format("%.2f", tax);
//    }
//
//    public String getTotal() {
//        return String.format("%.2f", total);
}
