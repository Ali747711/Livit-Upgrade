package com.codewithali.livitspringbackend.member;

import com.codewithali.livitspringbackend.auth.JwtService;
import com.codewithali.livitspringbackend.auth.dto.LoginRequest;
import com.codewithali.livitspringbackend.auth.dto.LoginResponse;
import com.codewithali.livitspringbackend.member.dto.MemberResponse;
import com.codewithali.livitspringbackend.member.dto.SignupRequest;
import com.codewithali.livitspringbackend.member.enums.MemberAuthType;
import com.codewithali.livitspringbackend.member.enums.MemberType;
//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Transactional
    public MemberResponse signup(SignupRequest request){
        // 1. Uniqueness checks
        if(memberRepository.existsByMemberNick(request.getMemberNick())){
            throw new IllegalStateException("memberNick already taken!");
        }
        if(memberRepository.existsByMemberPhone(request.getMemberPhone())){
            throw new IllegalStateException("memberPhone already taken!");
        }

        // 2. Hash the password
        String hashed = passwordEncoder.encode(request.getMemberPassword());

        // 3. Build the entity (defaults from @Builder .Default kick in for status, etc. )
        Member member = Member.builder()
                .memberNick(request.getMemberNick())
                .memberPhone(request.getMemberPhone())
                .memberPassword(hashed)
                .memberType(request.getMemberType() != null ? request.getMemberType() : MemberType.USER)
                .memberAuthType(request.getMemberAuthType() != null ? request.getMemberAuthType() : MemberAuthType.EMAIL)
                .build();

        // 4. Persist
        Member saved = memberRepository.save(member);

        // 5. Convert to safe response DTO
        return MemberResponse.fromEntity(saved);

    };

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByMemberNick(request.getMemberNick())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getMemberPassword(), member.getMemberPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(member);

        return LoginResponse.builder()
                .accessToken(token)
                .member(MemberResponse.fromEntity(member))
                .build();
    }
}
