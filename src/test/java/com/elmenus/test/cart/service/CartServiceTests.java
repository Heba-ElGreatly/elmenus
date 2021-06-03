package com.elmenus.test.cart.service;

import com.elmenus.ElmenusApp;
import com.elmenus.cart.dto.CartDTO;
import com.elmenus.cart.service.CartServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Heba.ElGreatly on 6/3/2021.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElmenusApp.class)
@PropertySource("classpath:application.properties")
public class CartServiceTests {

    @SpyBean
    private CartServiceImpl cartService;


    @Test
    @Transactional
    public void verifyUserEmail(){
        CartDTO cartDto = cartService.getUserCartItems(1);
        assertEquals(cartDto.getUserEmail(), "heba.elgreatly@gmail.com");
    }

    @Test
    @Transactional
    public void verifyCartIsNotEmpty(){
        CartDTO cartDto = cartService.getUserCartItems(1);
        assertThat(cartDto.getCartItems().size(), Matchers.greaterThan(0));
    }

}
