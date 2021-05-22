package com.elmenus.item.exception;

import com.elmenus.exception.BusinessException;

public class ItemNotFoundException extends BusinessException {

    public ItemNotFoundException() {
        super("No item found with the following name");
    }
}
