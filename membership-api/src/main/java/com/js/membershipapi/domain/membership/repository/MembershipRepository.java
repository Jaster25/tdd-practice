package com.js.membershipapi.domain.membership.repository;

import com.js.membershipapi.domain.membership.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findAllByMemberId(Long memberId);
}
