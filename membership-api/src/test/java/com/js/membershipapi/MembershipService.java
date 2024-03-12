package com.js.membershipapi;

import com.js.membershipapi.domain.membership.entity.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {

    public List<Membership> getMemberships(Long memberId) {
        throw new IllegalArgumentException("존재하지 않는 멤버입니다.");
    }
}
