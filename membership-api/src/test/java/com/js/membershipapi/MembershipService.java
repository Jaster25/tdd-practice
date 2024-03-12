package com.js.membershipapi;

import com.js.membershipapi.domain.member.repository.MemberRepository;
import com.js.membershipapi.domain.membership.entity.Membership;
import com.js.membershipapi.domain.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MemberRepository memberRepository;
    private final MembershipRepository membershipRepository;

    public List<Membership> getMemberships(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));

        return membershipRepository.findAllByMemberId(memberId);
    }

    public void getMembership(Long membershipId) {
    }
}
