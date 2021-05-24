package com.elmenus.order.service;

import com.elmenus.cart.dto.CartDTO;
import com.elmenus.order.model.Orders;

import java.util.List;

public interface OrderService {

    List<Orders> getAllOrders();

    CartDTO getUserPreOrderItems(Integer orderId);

}
