package com.codewithali.livitspringbackend.auth;


import com.codewithali.livitspringbackend.auth.dto.LoginRequest;
import com.codewithali.livitspringbackend.auth.dto.LoginResponse;
import com.codewithali.livitspringbackend.member.MemberService;
import com.codewithali.livitspringbackend.member.dto.MemberResponse;
import com.codewithali.livitspringbackend.member.dto.SignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@Valid @RequestBody SignupRequest request){
        MemberResponse response = memberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(memberService.login(request));
    }

}
