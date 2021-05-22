package com.elmenus.order.service;

import com.elmenus.order.dao.OrderRepository;
import com.elmenus.order.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
