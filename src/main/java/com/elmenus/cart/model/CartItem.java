package com.elmenus.cart.model;

import com.elmenus.item.model.Item;
import com.elmenus.order.model.Orders;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CartItem")
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItemId")
    private Integer id;

    @Column(name="itemQuantity")
    private Integer itemQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private Orders orders;

}
