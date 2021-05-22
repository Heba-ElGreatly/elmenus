package com.elmenus.cart.service;


import com.elmenus.cart.dao.CartItemsRepository;
import com.elmenus.cart.dto.CartDTO;
import com.elmenus.cart.dto.CartItemDTO;
import com.elmenus.cart.model.CartItem;
import com.elmenus.cart.service.payment.CreditCardPayment;
import com.elmenus.cart.exception.CartHasNoItemsException;
import com.elmenus.order.exception.MaximumOrderCostException;
import com.elmenus.order.exception.MinimumOrderCostException;
import com.elmenus.cart.exception.SoldOutCartItemException;
import com.elmenus.mapper.CartItemMapper;
import com.elmenus.user.dao.UserRepository;
import com.elmenus.user.model.User;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private static final Integer ZERO = 0;
    private static final Integer MINIMUM_AMOUNT = 100;
    private static final Integer MAXIMUM_AMOUNT = 1500;
    private static final Integer SHIPPING_TAX_AMOUNT = 60;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardPayment creditCardPayment;

    @Override
    public CartDTO getUserCartItems(Integer userId) {

        CartDTO cartDTO = new CartDTO();

        User loggedInUser = userRepository.getOne(userId);
        cartDTO.setUserEmail(loggedInUser.getEmail());

       List<CartItem> items = cartItemsRepository.findCartItemsByUserId(userId)
                .orElseThrow(() -> new CartHasNoItemsException());

        List<CartItemDTO> cartItemsDto = items
                .stream()
                .map(cartItem -> cartItemMapper.mapEntityToDTO(cartItem)).collect(Collectors.toList());

        cartDTO.setCartItems(cartItemsDto);

        return cartDTO;
    }

    @Override
    public void applyCartCheckout(CartDTO cartDto) {
        String paymentLink = null;

//        Validate the basket items availability
        validatingItemsAvailability(cartDto);
//        Validate with the total basket money value above 100
        validatingOrderCost(cartDto);

    }

    @Override
    public String payingTotalAmount(CartDTO cartDTO) {
        //TODO fixing thrown exception
        String paymentLink = null;
        try {
            paymentLink = creditCardPayment.authorizePayment(cartDTO);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return paymentLink;
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
        Double total = cartDto.getCartItems().stream().map(cartItem -> {
            return cartItem.getItem().getPrice() * cartItem.getItemQuantity().intValue();
        }).findFirst().get();

        if (total > ZERO && total < MINIMUM_AMOUNT) {
            throw new MinimumOrderCostException();
        }

        if (total > MAXIMUM_AMOUNT) {
            throw new MaximumOrderCostException();
        }
        cartDto.setSubTotal(total);
        cartDto.setTotal(cartDto.getSubTotal() + SHIPPING_TAX_AMOUNT );
    }

}
