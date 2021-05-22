package com.elmenus.cart.dto;

import com.elmenus.item.dto.ItemDTO;
import com.elmenus.item.model.Item;
import com.elmenus.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemDTO {

    private Integer itemQuantity;

    private ItemDTO item;

}
