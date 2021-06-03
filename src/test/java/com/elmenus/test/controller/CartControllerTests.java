package com.elmenus.test.controller;

import com.elmenus.cart.controller.CartController;
import com.elmenus.cart.dto.CartDTO;
import com.elmenus.cart.service.CartServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Heba.ElGreatly on 6/3/2021.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CartServiceImpl cartService;


    @Test
    public void loadUserMockCart() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotal(500.0);
        cartDTO.setSubTotal(450.0);

        when(cartService.getUserCartItems(1)).thenReturn(cartDTO);
        mockMvc.perform(get("/api/cart/load-cart/{userId}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(cartDTO.getTotal()));

    }
}
