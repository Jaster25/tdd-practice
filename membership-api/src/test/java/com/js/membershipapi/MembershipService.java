package com.js.membershipapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    public void getMemberships(Long memberId) {
        throw new IllegalArgumentException("존재하지 않는 멤버입니다.");
    }
}
