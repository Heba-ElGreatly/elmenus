package com.elmenus.order.exception;

import com.elmenus.exception.BusinessException;

public class MaximumOrderCostException extends BusinessException {

    public MaximumOrderCostException() {
        super("Sorry, we can't proceed with order costs above 1500LE");
    }
}
