package com.elmenus.item.exception;

import com.elmenus.exception.BusinessException;

public class AddNewItemException extends BusinessException {

    public AddNewItemException() {
        super("Failed to add item");
    }

}
