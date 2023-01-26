package com.example.ecommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupResponseDto {
    private String status;
    private String message;
}
