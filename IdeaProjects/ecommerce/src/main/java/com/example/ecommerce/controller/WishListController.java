package com.example.ecommerce.controller;

import com.example.ecommerce.config.ApiResponse;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.exceptions.AuthenticationFailException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.model.WishList;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.service.AuthenticationService;
import com.example.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WishListController {
    @Autowired
    WishListService wishListService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody ProductDto productDto,
                                                   @RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productRepository.getById(productDto.getId());

        WishList wishList = new WishList(user, product);
        wishListService.createWishList(wishList);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to wishlist"), HttpStatus.CREATED);

    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable(name = "token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        List<WishList> wishLists = wishListService.readWishList(user);

        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList : wishLists) {
            productDtos.add(new ProductDto(wishList.getProduct()));
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}
