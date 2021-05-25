package com.elmenus.order.dto;

import com.elmenus.cart.dto.CartItemDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private Integer orderId;
    private String userEmail;
    private List<CartItemDTO> items;
    private Timestamp createdOn;
    private double total;
    private boolean ordered;
}
