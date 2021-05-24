package com.elmenus.item.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDTO {

    private String itemName;
    private double price;
    private boolean available;

}