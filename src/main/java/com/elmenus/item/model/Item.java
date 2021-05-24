package com.elmenus.item.model;

import com.elmenus.cart.model.CartItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Item")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer id;

    @Column(name = "item_name")
    @NotNull
    private String itemName;

    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "available")
    private boolean available;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

}
