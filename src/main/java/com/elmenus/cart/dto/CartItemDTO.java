package com.elmenus.cart.dto;

import com.elmenus.item.dto.ItemDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemDTO {

    private Integer itemQuantity;

    private ItemDTO item;

}
