package com.elmenus.item.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@Builder
@Setter
@Getter
public class ItemDTO {

    private String itemName;
//    private Integer stock;
    private double price;
    private boolean available;
}
