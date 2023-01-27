package com.example.ecommerce.controller;

import com.example.ecommerce.dto.Checkout.CheckoutItemDto;
import com.example.ecommerce.dto.Checkout.StripResponse;
import com.example.ecommerce.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtos) throws StripeException {
        Session session = orderService.createSession(checkoutItemDtos);
        StripResponse stripResponse = new StripResponse(session.getId());

        return new ResponseEntity<StripResponse>(stripResponse, HttpStatus.OK);
    }
}
