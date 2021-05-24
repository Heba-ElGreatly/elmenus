package com.elmenus.cart.service;


import com.elmenus.cart.dao.CartItemsRepository;
import com.elmenus.cart.dto.CartDTO;
import com.elmenus.cart.dto.CartItemDTO;
import com.elmenus.cart.exception.CartHasNoItemsException;
import com.elmenus.cart.model.CartItem;
import com.elmenus.mapper.CartItemMapper;
import com.elmenus.user.dao.UserRepository;
import com.elmenus.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private UserRepository userRepository;

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

}
