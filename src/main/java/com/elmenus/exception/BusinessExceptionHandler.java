package com.elmenus.exception;


import com.elmenus.cart.exception.CartHasNoItemsException;
import com.elmenus.cart.exception.SoldOutCartItemException;
import com.elmenus.item.exception.AddNewItemException;
import com.elmenus.item.exception.ItemAlreadyExistException;
import com.elmenus.item.exception.ItemNotFoundException;
import com.elmenus.order.exception.MaximumOrderCostException;
import com.elmenus.order.exception.MinimumOrderCostException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> generalExcpetionHandler(Exception ex,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CartHasNoItemsException.class)
    public ResponseEntity<?> resourceNotFoundException(CartHasNoItemsException ex,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SoldOutCartItemException.class)
    public ResponseEntity<?> resourceNotFoundException(SoldOutCartItemException ex,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddNewItemException.class)
    public ResponseEntity<?> resourceNotFoundException(AddNewItemException ex,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemAlreadyExistException.class)
    public ResponseEntity<?> resourceNotFoundException(ItemAlreadyExistException ex,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ItemNotFoundException ex,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MinimumOrderCostException.class)
    public ResponseEntity<?> resourceNotFoundException(MinimumOrderCostException ex,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaximumOrderCostException.class)
    public ResponseEntity<?> resourceNotFoundException(MaximumOrderCostException ex,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
