package com.example.ecommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SignInResponseDto {
    private String status;
    private String token;
}
