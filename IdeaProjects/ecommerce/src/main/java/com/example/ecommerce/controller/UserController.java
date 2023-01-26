package com.example.ecommerce.controller;

import com.example.ecommerce.dto.user.SignupDto;
import com.example.ecommerce.dto.user.SignupResponseDto;
import com.example.ecommerce.exceptions.CustomException;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public SignupResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }
}
