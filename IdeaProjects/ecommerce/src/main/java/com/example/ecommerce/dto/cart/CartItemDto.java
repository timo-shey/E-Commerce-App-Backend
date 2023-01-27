package com.example.ecommerce.dto.cart;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
public class CartItemDto {
    private Integer id;
    @NotNull
    private Integer quantity;
    @NotNull
    private Product product;

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
}
