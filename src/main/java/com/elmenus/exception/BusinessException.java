package com.elmenus.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException{

	public BusinessException(String msg) {
		super(msg);
	}

}
