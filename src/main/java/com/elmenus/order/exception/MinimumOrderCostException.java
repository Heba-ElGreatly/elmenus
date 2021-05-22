package com.elmenus.order.exception;

import com.elmenus.exception.BusinessException;

public class MinimumOrderCostException extends BusinessException {

    public MinimumOrderCostException() {
        super("Minimum Order Cost is 100LE");
    }
}
