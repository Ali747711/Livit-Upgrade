package com.codewithali.livitspringbackend.member.dto;

import com.codewithali.livitspringbackend.member.Member;
import com.codewithali.livitspringbackend.member.enums.MemberAuthType;
import com.codewithali.livitspringbackend.member.enums.MemberStatus;
import com.codewithali.livitspringbackend.member.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String memberNick;
    private String memberPhone;
    private String memberFullName;
    private String memberImage;
    private MemberType memberType;
    private MemberStatus memberStatus;
    private MemberAuthType memberAuthType;
    private Integer memberFollowers;
    private Integer memberFollowings;
    private Integer memberPoints;
    private LocalDateTime createdAt;

    public static MemberResponse fromEntity(Member m) {
        return MemberResponse.builder()
                .id(m.getId())
                .memberNick(m.getMemberNick())
                .memberPhone(m.getMemberPhone())
                .memberFullName(m.getMemberFullName())
                .memberImage(m.getMemberImage())
                .memberType(m.getMemberType())
                .memberStatus(m.getMemberStatus())
                .memberAuthType(m.getMemberAuthType())
                .memberFollowers(m.getMemberFollowers())
                .memberFollowings(m.getMemberFollowings())
                .memberPoints(m.getMemberPoints())
                .createdAt(m.getCreatedAt())
                .build();
    }
}
