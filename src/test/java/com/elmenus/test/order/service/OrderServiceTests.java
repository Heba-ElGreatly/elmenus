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
import com.elmenus.order.service.OrderService;
import com.elmenus.order.service.OrderServiceImpl;
import com.elmenus.order.util.OrderUtil;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElmenusApp.class)
@PropertySource("classpath:application.properties")
@AutoConfigureMockMvc
public class OrderServiceTests {

//    @Inject
//    private OrderServiceImpl orderService;

//    @Autowired
//    private MockMvc mockMvc;


    @SpyBean
    private OrderServiceImpl orderService;// = new OrderServiceImpl();

//    @Autowired
//    private OrderService orderService = new OrderServiceImpl();

    @Autowired
    private OrderUtil orderUtil;

    @Inject
    private CartItemsRepository cartItemsRepository;

    @SpyBean
    private CartItemMapper cartItemMapper ;//= new CartItemMapper();

    @SpyBean
    private ItemMapper itemMapper ;//= new ItemMapper();


//    @Before
//    public void setUp() {
////        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
////
////        ItemDTO burger = new ItemDTO();
////        burger.setAvailable(true);
////        burger.setItemName("burger");
////        burger.setPrice(70.0);
////
////        CartItemDTO burgerCartItemDTO = new CartItemDTO();
////        burgerCartItemDTO.setItemQuantity(2);
////        burgerCartItemDTO.setItem(burger);
////
////
////        ItemDTO fish = new ItemDTO();
////        fish.setAvailable(true);
////        fish.setItemName("fish");
////        fish.setPrice(97.0);
////
////        CartItemDTO fishCartItemDTO = new CartItemDTO();
////        fishCartItemDTO.setItemQuantity(2);
////        fishCartItemDTO.setItem(fish);
////
////        cartItemDTOs.add(burgerCartItemDTO);
////        cartItemDTOs.add(fishCartItemDTO);
////
////        CartDTO cartDto = new CartDTO();
////        cartDto.setOrderId(3);
////        cartDto.setCartItems(cartItemDTOs);
////        cartDto.setSubTotal((burgerCartItemDTO.getItemQuantity() * burger.getPrice()) + (fishCartItemDTO.getItemQuantity() * fish.getPrice()));
////        cartDto.setTotal(cartDto.getSubTotal()*0.14);
////
////        Mockito.when(orderService.getUserPreOrderItems(3)).thenReturn(cartDto);
//
//        List<CartItem> cartItems = new ArrayList<>();
//
//        Item burger = new Item();
//        burger.setAvailable(true);
//        burger.setItemName("burger");
//        burger.setPrice(70.0);
//
//        CartItem burgerCartItem = new CartItem();
//        burgerCartItem.setItemQuantity(2);
//        burgerCartItem.setItem(burger);
//
//        Item fish = new Item();
//        fish.setAvailable(true);
//        fish.setItemName("fish");
//        fish.setPrice(97.0);
//
//        CartItem fishCartItem = new CartItem();
//        fishCartItem.setItemQuantity(2);
//        fishCartItem.setItem(fish);
//
//        cartItems.add(burgerCartItem);
//        cartItems.add(fishCartItem);
//
//        Mockito.when(cartItemsRepository.getUserOrdersByCartItemOrderId(3)).thenReturn(cartItems);
//    }

//    @Before
//    public void createBean(){
//        Mockito.when(cartItemMapper.mapEntityToDTO()).thenReturn();
//    }

    @Test
    public void testGet() {
        CartDTO s = orderService.getUserPreOrderItems(3);
        List<CartItem> h = cartItemsRepository.getUserOrdersByCartItemOrderId(3);
        System.out.println(h);

        assertEquals(cartItemsRepository.getUserOrdersByCartItemOrderId(3).stream().map(cartItem -> cartItemMapper.mapEntityToDTO(cartItem)).collect(Collectors.toList()).size(),
                orderService.getUserPreOrderItems(3).getCartItems().size());

//        assertEquals(cartItemsRepository.getUserOrdersByCartItemOrderId(3).stream().map(cartItem -> cartItemMapper.mapEntityToDTO(cartItem)).collect(Collectors.toList()).size(),
//                orderService.getUserPreOrderItems(3).getCartItems().size());
    }

    @Test
    public void getOrders() {
        CartDTO result = orderService.getUserPreOrderItems(3);

        assertThat(result.getTotal(), Matchers.greaterThan(100.0));

    }
}
