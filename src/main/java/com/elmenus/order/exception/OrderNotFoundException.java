package com.elmenus.order.exception;

import com.elmenus.exception.BusinessException;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException() {
        super("No items to be ordered found");
    }
}
