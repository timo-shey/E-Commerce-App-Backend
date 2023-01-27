package com.example.ecommerce.exceptions;

public class CartItemsNotExistException extends Exception{
    public CartItemsNotExistException(String msg) {
        super(msg);
    }
}
