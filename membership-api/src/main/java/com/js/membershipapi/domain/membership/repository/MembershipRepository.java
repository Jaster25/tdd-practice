package com.js.membershipapi.domain.membership.repository;

import com.js.membershipapi.domain.membership.entity.Membership;
import com.js.membershipapi.domain.membership.entity.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findAllByMemberId(Long memberId);

    Optional<Membership> findByMemberIdAndMembershipType(Long memberId, MembershipType membershipType);
}
