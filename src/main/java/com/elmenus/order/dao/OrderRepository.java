package com.elmenus.order.dao;

import com.elmenus.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Integer> {

}
