package com.elmenus.cart.exception;

import com.elmenus.exception.BusinessException;

public class SoldOutCartItemException extends BusinessException {

    public SoldOutCartItemException() {
        super("Sorry, Cart Contains Sold out items");
    }
}
