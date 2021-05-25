package com.elmenus.mapper;

import com.elmenus.order.dto.OrderDTO;
import com.elmenus.order.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    @Autowired
    private CartItemMapper cartItemMapper;

    public Orders mapDtoToEntity(OrderDTO orderDTO){
        Orders order = new Orders();
        order.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        order.setCartItems(orderDTO.getItems().stream().map(item -> cartItemMapper.mapDtoToEntity(item)).collect(Collectors.toList()));
        order.setCreatedOn(orderDTO.getCreatedOn());
        order.setOrdered(orderDTO.isOrdered());
        order.setTotal(orderDTO.getTotal());
        return order;
    }

    public OrderDTO mapEntityToDTO(Orders order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserEmail(order.getUser().getEmail());
        orderDTO.setItems(order.getCartItems().stream().map(item -> cartItemMapper.mapEntityToDTO(item)).collect(Collectors.toList()));
        orderDTO.setCreatedOn(order.getCreatedOn());
        orderDTO.setOrdered(order.isOrdered());
        orderDTO.setTotal(order.getTotal());
        return orderDTO;
    }
}
