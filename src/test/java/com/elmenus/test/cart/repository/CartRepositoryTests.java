package com.elmenus.test.cart.repository;

import com.elmenus.ElmenusApp;
import com.elmenus.cart.dao.CartItemsRepository;
import com.elmenus.cart.model.CartItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Heba.ElGreatly on 6/3/2021.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElmenusApp.class)
@PropertySource("classpath:application.properties")
public class CartRepositoryTests {

    @Inject
    private CartItemsRepository cartItemsRepository;

    @Test
    public void checkBurgerItemAvailability(){
        List<CartItem> cartItem = cartItemsRepository.getUserOrdersByCartItemOrderId(3);
        assertTrue(cartItem.get(0).getItem().isAvailable());
    }

    @Test
    public void verifyCartIsNotNull(){
        Optional<List<CartItem>> cartItem = cartItemsRepository.findCartItemsByUserId(1);
        assertNotNull(cartItem);
    }
}
