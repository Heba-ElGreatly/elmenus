package com.elmenus.item.exception;

import com.elmenus.exception.BusinessException;

public class ItemAlreadyExistException extends BusinessException {

        public ItemAlreadyExistException() {
        super("Item already exists");
    }

}
