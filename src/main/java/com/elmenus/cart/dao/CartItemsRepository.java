package com.elmenus.cart.dao;

import com.elmenus.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemsRepository extends JpaRepository<CartItem,Integer> {

    @Query(value = "select cartItem from CartItem cartItem Join cartItem.orders o where o.orderId = cartItem.orders and o.ordered=0 and user_id=:user_id")
    Optional<List<CartItem>> findCartItemsByUserId(@Param("user_id")Integer userId);
}
