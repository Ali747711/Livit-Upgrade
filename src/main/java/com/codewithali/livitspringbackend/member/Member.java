package com.codewithali.livitspringbackend.member;

import com.codewithali.livitspringbackend.member.enums.MemberAuthType;
import com.codewithali.livitspringbackend.member.enums.MemberStatus;
import com.codewithali.livitspringbackend.member.enums.MemberType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private MemberType memberType = MemberType.USER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private MemberStatus memberStatus = MemberStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private MemberAuthType memberAuthType = MemberAuthType.EMAIL;

    @Column(nullable = false, unique = true, length = 50)
    private String memberPhone;

    @Column(nullable = false, unique = true, length = 50)
    private String memberNick;

    @Column(nullable = false)
    private String memberPassword;

    @Column(length = 100)
    private String memberFullName;

    @Column(length = 500)
    @Builder.Default
    private String memberImage = "";

    @Column(length = 200)
    private String memberAddress;

    @Column(length = 1000)
    private String memberDesc;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberProperties = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberArticles = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberFollowers = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberFollowings = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberPoints = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberLikes = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberViews = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberComments = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberRank = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberWarnings = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer memberBlocks = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;


}
