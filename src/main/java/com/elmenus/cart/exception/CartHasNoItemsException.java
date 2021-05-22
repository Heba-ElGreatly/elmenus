package com.elmenus.cart.exception;

import com.elmenus.exception.BusinessException;

public class CartHasNoItemsException extends BusinessException {

    public CartHasNoItemsException() {
        super("No items added to cart yet");
    }
}
