package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.model.WishList;
import com.example.ecommerce.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;

    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }
    public List<WishList> readWishList(User user) {
        return wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
    }
}
