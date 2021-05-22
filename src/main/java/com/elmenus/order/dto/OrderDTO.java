package com.elmenus.order.dto;

import com.elmenus.item.model.Item;
import com.elmenus.user.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private User user;
    private List<Item> items;
    private Integer total;
    private boolean ordered;
}
