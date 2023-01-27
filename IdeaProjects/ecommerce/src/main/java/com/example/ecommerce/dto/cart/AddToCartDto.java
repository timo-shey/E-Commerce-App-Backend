package com.example.ecommerce.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddToCartDto {
    private Integer id;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;
}
