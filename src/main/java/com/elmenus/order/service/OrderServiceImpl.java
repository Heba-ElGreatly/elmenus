package com.elmenus.order.service;

import com.elmenus.cart.dao.CartItemsRepository;
import com.elmenus.cart.dto.CartDTO;
import com.elmenus.cart.dto.CartItemDTO;
import com.elmenus.cart.exception.CartHasNoItemsException;
import com.elmenus.cart.exception.SoldOutCartItemException;
import com.elmenus.cart.model.CartItem;
import com.elmenus.mapper.CartItemMapper;
import com.elmenus.order.dao.OrderRepository;
import com.elmenus.order.exception.MaximumOrderCostException;
import com.elmenus.order.exception.MinimumOrderCostException;
import com.elmenus.order.model.Orders;
import com.elmenus.order.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Integer ZERO = 0;
    private static final Integer MINIMUM_AMOUNT = 100;
    private static final Integer MAXIMUM_AMOUNT = 1500;
    private static final Integer SHIPPING_AMOUNT = 60;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private OrderUtil orderUtil;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;


    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public CartDTO getUserPreOrderItems(Integer orderId) {
        CartDTO cartDto = new CartDTO();
        List<CartItem> items = cartItemsRepository.getUserOrdersByCartItemOrderId(orderId);

        if(!items.isEmpty()) {
            List<CartItemDTO> cartItemsDto = items.stream().map(item -> cartItemMapper.mapEntityToDTO(item)).collect(Collectors.toList());
            cartDto.setOrderId(orderId);
            cartDto.setCartItems(cartItemsDto);

            //Validate the basket items availability
            validatingItemsAvailability(cartDto);
            //Validate with the total basket money value above 100
            validatingOrderCost(cartDto);

            return cartDto;

        }else{
            throw new CartHasNoItemsException();
        }
    }

    //Validate the basket items availability
    private void validatingItemsAvailability(CartDTO cartDto) {
        Optional<CartItemDTO> outOfStockItems = cartDto.getCartItems().stream()
                .filter(cartItem -> !cartItem.getItem().isAvailable()).findAny();

        if (outOfStockItems.isPresent()) {
            throw new SoldOutCartItemException();
        }
    }


    //Validate with the total basket money value above 100
    private void validatingOrderCost(CartDTO cartDto) {
//        double total = cartDto.getCartItems().stream().map(cartItem -> {
//            return cartItem.getItem().getPrice()*(cartItem.getItemQuantity());
//        }).findFirst().get();
        double total = cartDto.getCartItems().stream().map(cartItem -> {
            return cartItem.getItem().getPrice()*(cartItem.getItemQuantity());
        }).collect(Collectors.summingDouble(Double::doubleValue));


        if (total > ZERO && total < MINIMUM_AMOUNT) {
            throw new MinimumOrderCostException();
        }

        if (total > MAXIMUM_AMOUNT) {
            throw new MaximumOrderCostException();
        }
        cartDto.setSubTotal(total);
        cartDto.setTax(total * 0.14);
        cartDto.setTotal(cartDto.getSubTotal() + cartDto.getTax()+ SHIPPING_AMOUNT );
    }
}
