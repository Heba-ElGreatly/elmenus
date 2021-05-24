package com.elmenus.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Builder
@Getter @Setter
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;
}
