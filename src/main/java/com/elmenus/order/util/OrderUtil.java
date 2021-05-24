package com.elmenus.order.util;

import com.elmenus.cart.dto.CartDTO;
import com.elmenus.cart.dto.CartItemDTO;
import com.elmenus.item.dto.ItemDTO;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OrderUtil {

    public Long calculateOrderAmountInPiasters(CartDTO dto) {
        BigDecimal amount = new BigDecimal("0");

        if (dto != null && dto.getCartItems() != null) {
            for (CartItemDTO lineItem : dto.getCartItems()) {
                if (lineItem.getItemQuantity() > 0) {
                    ItemDTO product = lineItem.getItem();
                    Money productPrice = Money.of(product.getPrice(),CurrencyUtil.EGP);
                    Money totalCost = productPrice.multiply(lineItem.getItemQuantity());
                    amount = totalCost.getNumberStripped();
                }
            }
            ;
        }

        amount = amount.multiply(new BigDecimal("100"));

        return amount.longValue();
    }
}
