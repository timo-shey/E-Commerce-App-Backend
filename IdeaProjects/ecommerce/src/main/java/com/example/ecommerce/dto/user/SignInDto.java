package com.example.ecommerce.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInDto {
    private String email;
    private String password;
}
