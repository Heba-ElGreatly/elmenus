package com.elmenus.cart.controller;

import com.elmenus.cart.dto.CartDTO;
import com.elmenus.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping(value = "/load-cart/{userId}")
    ResponseEntity<CartDTO> loadUserCart(@PathVariable(name = "userId") Integer userId) {
        CartDTO cartItems = cartService.getUserCartItems(userId);
        return new ResponseEntity(Optional.of(cartService.getUserCartItems(userId)), HttpStatus.OK);
    }

    @GetMapping(value = "/validate-data/{userId}")
    ResponseEntity<?> validatingItems(@PathVariable(name = "userId") Integer userId) {
        CartDTO cartItems = cartService.getUserCartItems(userId);
        return ResponseEntity.noContent().build();
    }
}
