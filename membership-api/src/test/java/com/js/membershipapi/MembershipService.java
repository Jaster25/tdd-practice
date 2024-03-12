package com.js.membershipapi;

import com.js.membershipapi.domain.member.entity.Member;
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

    public Membership getMembership(Long membershipId) {
        return membershipRepository.findById(membershipId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버십입니다."));
    }

    public void verify(Member member, Membership membership) {
        throw new IllegalArgumentException("권한이 없습니다.");
    }
}
