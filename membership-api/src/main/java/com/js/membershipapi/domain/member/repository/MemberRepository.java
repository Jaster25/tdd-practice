package com.js.membershipapi.domain.member.repository;

import com.js.membershipapi.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
