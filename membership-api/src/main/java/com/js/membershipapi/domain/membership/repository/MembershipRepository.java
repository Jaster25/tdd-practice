package com.js.membershipapi.domain.membership.repository;

import com.js.membershipapi.domain.membership.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
