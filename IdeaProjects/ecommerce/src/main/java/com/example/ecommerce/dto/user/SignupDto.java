package com.example.ecommerce.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {
    private String firstName;
    private String lastname;
    private String email;
    private String password;
}
