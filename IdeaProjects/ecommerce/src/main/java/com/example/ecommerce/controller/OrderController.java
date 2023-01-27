package com.example.ecommerce.controller;

import com.example.ecommerce.config.ApiResponse;
import com.example.ecommerce.dto.Checkout.CheckoutItemDto;
import com.example.ecommerce.dto.Checkout.StripResponse;
import com.example.ecommerce.exceptions.AuthenticationFailException;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.AuthenticationService;
import com.example.ecommerce.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtos) throws StripeException {
        Session session = orderService.createSession(checkoutItemDtos);
        StripResponse stripResponse = new StripResponse(session.getId());

        return new ResponseEntity<StripResponse>(stripResponse, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId)
            throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        orderService.placeOrder(user, sessionId);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        List<Order> orderDtoList = orderService.listOrders(user);
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }
}
