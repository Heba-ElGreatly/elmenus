package com.elmenus.mapper;

import com.elmenus.cart.dto.CartItemDTO;
import com.elmenus.cart.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartItemMapper {

    @Autowired
    private ItemMapper itemMapper;

    public CartItem mapDtoToEntity(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();

        cartItem.setItem(itemMapper.mapDtoToEntity(cartItemDTO.getItem()));
        cartItem.setItemQuantity(cartItemDTO.getItemQuantity());

        return cartItem;
    }

    public CartItemDTO mapEntityToDTO(CartItem entity) {
        CartItemDTO cartDTO = new CartItemDTO();

        cartDTO.setItem(itemMapper.mapEntityToDTO(entity.getItem()));
        cartDTO.setItemQuantity(entity.getItemQuantity());

        return cartDTO;
    }
}
