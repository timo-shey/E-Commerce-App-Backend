package com.example.ecommerce.dto;

import com.example.ecommerce.model.Product;
import lombok.*;

import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDto {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String imageUrl;
    @NotNull
    private double price;
    @NotNull
    private String description;
    @NotNull
    private Integer categoryId;

    public ProductDto(Product product) {
    }

//    public ProductDto(Product product) {
//        this.setId(product.getId());
//        this.setName(product.getName());
//        this.setImageUrl(product.getImageUrl());
//        this.setDescription(product.getDescription());
//        this.setPrice(product.getPrice());
//        this.setCategoryId(product.getCategory().getId());
//    }
}
