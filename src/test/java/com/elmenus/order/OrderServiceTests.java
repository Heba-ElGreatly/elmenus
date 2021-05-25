//package com.elmenus.order;
//
//
//import com.elmenus.cart.dao.CartItemsRepository;
//import com.elmenus.cart.dto.CartDTO;
//import com.elmenus.order.service.OrderService;
//import com.elmenus.order.service.OrderServiceImpl;
//import com.elmenus.order.util.OrderUtil;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//import static org.mockito.Mockito.when;
//
//
//@SpringBootTest
//@PropertySource("classpath:application.properties")
//public class OrderServiceTests {
//
////    @Inject
////    private OrderServiceImpl orderService;
//
//    @MockBean
//    private MockMvc mockMvc;
//
//
//
//    @InjectMocks
//    private OrderService orderService = new OrderServiceImpl();
//    @Autowired
//    private OrderUtil orderUtil;
//
//    @Mock
//    private CartItemsRepository cartItemsRepository;
//
//    @BeforeEach
//    void setMockOutput() {
//        when(orderService.getUserPreOrderItems(3)).thenReturn(orderService.getUserPreOrderItems(3));
//    }
//
//
//    @Test
//    public void testGet() {
//        assertEquals(cartItemsRepository.getUserOrdersByCartItemOrderId(3), orderService.getUserPreOrderItems(3));
//    }
//
//    @Test
//    public void getOrders() {
//        CartDTO result = orderService.getUserPreOrderItems(3);
//
//        assertThat(result.getTotal(), Matchers.greaterThan(100.0));
//
//    }
//}
