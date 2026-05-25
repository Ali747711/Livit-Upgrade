package com.codewithali.livitspringbackend.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String memberNick;
    @NotBlank
    private String memberPassword;
}
