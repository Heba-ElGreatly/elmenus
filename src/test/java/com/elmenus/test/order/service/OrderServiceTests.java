package com.elmenus.test.order.service;


import com.elmenus.ElmenusApp;
import com.elmenus.cart.dao.CartItemsRepository;
import com.elmenus.cart.dto.CartDTO;
import com.elmenus.cart.dto.CartItemDTO;
import com.elmenus.cart.model.CartItem;
import com.elmenus.item.dto.ItemDTO;
import com.elmenus.item.model.Item;
import com.elmenus.mapper.CartItemMapper;
import com.elmenus.mapper.ItemMapper;
import com.elmenus.order.service.OrderServiceImpl;
import com.elmenus.order.util.OrderUtil;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElmenusApp.class)
@PropertySource("classpath:application.properties")
@AutoConfigureMockMvc
public class OrderServiceTests {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderUtil orderUtil;

    @Mock
    private CartItemsRepository cartItemsRepository;

    @Mock
    private CartItemMapper cartItemMapper ;

    @Mock
    private ItemMapper itemMapper ;


    @Before
    public void setUp() {

        List<CartItem> cartItems = new ArrayList<>();

        Item burger = new Item();
        burger.setAvailable(true);
        burger.setItemName("burger");
        burger.setPrice(70.0);

        CartItem burgerCartItem = new CartItem();
        burgerCartItem.setItemQuantity(2);
        burgerCartItem.setItem(burger);

        Item fish = new Item();
        fish.setAvailable(true);
        fish.setItemName("fish");
        fish.setPrice(97.0);

        CartItem fishCartItem = new CartItem();
        fishCartItem.setItemQuantity(2);
        fishCartItem.setItem(fish);

        cartItems.add(burgerCartItem);
        cartItems.add(fishCartItem);

        Mockito.when(cartItemsRepository.getUserOrdersByCartItemOrderId(3)).thenReturn(cartItems);
    }

    @Before
    public void createBean(){

        CartItemDTO burgerCartItemDTO = new CartItemDTO();
        CartItemDTO fishCartItemDTO = new CartItemDTO();

        ItemDTO burgerDto = new ItemDTO();
        burgerDto.setAvailable(true);
        burgerDto.setItemName("burger");
        burgerDto.setPrice(70.0);

        burgerCartItemDTO.setItem(burgerDto);
        burgerCartItemDTO.setItemQuantity(2);


        ItemDTO fishDto = new ItemDTO();
        fishDto.setAvailable(true);
        fishDto.setItemName("fish");
        fishDto.setPrice(97.0);

        fishCartItemDTO.setItem(fishDto);
        fishCartItemDTO.setItemQuantity(2);

        when(cartItemMapper.mapEntityToDTO(any(CartItem.class))).thenReturn(burgerCartItemDTO).thenReturn(fishCartItemDTO);
    }

    @Test
    public void verifyCartSizeAndPreOrderItemsSizeAreEqual() {
        assertEquals(cartItemsRepository.getUserOrdersByCartItemOrderId(3).stream().map(cartItem -> cartItemMapper.mapEntityToDTO(cartItem)).collect(Collectors.toList()).size(),
                orderService.getUserPreOrderItems(3).getCartItems().size());

    }

    @Test
    public void verifyTotalOrderIsGreaterThanTheMinimum() {
        CartDTO result = orderService.getUserPreOrderItems(3);
        assertThat(result.getTotal(), Matchers.greaterThan(100.0));

    }
}
