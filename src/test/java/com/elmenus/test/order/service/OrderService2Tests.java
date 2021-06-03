package com.elmenus.test.order.service;

import com.elmenus.ElmenusApp;
import com.elmenus.cart.dao.CartItemsRepository;
import com.elmenus.mapper.CartItemMapper;
import com.elmenus.order.service.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


/**
 * Created by Heba.ElGreatly on 6/3/2021.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElmenusApp.class)
@PropertySource("classpath:application.properties")
public class OrderService2Tests {

    @Inject
    private CartItemsRepository cartItemsRepository;

    @SpyBean
    private CartItemMapper cartItemMapper;

    @SpyBean
    private OrderServiceImpl orderService;

    @Test
    public void verifyCartSizeAndPreOrderItemsSizeAreEqual() {
        assertEquals(cartItemsRepository.getUserOrdersByCartItemOrderId(3).stream().map(cartItem -> cartItemMapper.mapEntityToDTO(cartItem)).collect(Collectors.toList()).size(),
                orderService.getUserPreOrderItems(3).getCartItems().size());
    }
}
