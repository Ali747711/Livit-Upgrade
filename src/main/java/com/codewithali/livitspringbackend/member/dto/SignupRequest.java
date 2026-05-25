package com.codewithali.livitspringbackend.member.dto;

import com.codewithali.livitspringbackend.member.enums.MemberAuthType;
import com.codewithali.livitspringbackend.member.enums.MemberType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "memberNick is required!")
    @Size(min = 3, max = 50, message = "memberNick must be 3-50 chars")
    private String memberNick;

    @NotBlank(message = "memberPhone is required")
    @Size(min = 5, max = 50)
    private String memberPhone;

    @NotBlank(message = "memberPassword is required!")
    @Size(min = 6, max = 100, message = "memberPassword must be 6-100 chars!")
    private String memberPassword;

    private MemberType memberType;
    private MemberAuthType memberAuthType;
}
