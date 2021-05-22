package com.elmenus.cart.service;

import com.elmenus.cart.dto.CartDTO;


public interface CartService {

    CartDTO getUserCartItems(Integer userId) ;
    void applyCartCheckout(CartDTO cartDto) ;
    String payingTotalAmount(CartDTO cartDTO);
}
