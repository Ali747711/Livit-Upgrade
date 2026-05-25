package com.codewithali.livitspringbackend.auth.dto;

import com.codewithali.livitspringbackend.member.dto.MemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private MemberResponse member;
}
