package com.codewithali.livitspringbackend.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberNick(String memberNick);
    Optional<Member> findByMemberPhone(String memberPhone);

    boolean existsByMemberNick(String memberNick);
    boolean existsByMemberPhone(String memberPhone);
}
