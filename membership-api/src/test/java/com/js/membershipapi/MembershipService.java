package com.js.membershipapi;

import com.js.membershipapi.domain.member.entity.Member;
import com.js.membershipapi.domain.member.repository.MemberRepository;
import com.js.membershipapi.domain.membership.entity.Membership;
import com.js.membershipapi.domain.membership.entity.MembershipType;
import com.js.membershipapi.domain.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MembershipService {

    private final MemberRepository memberRepository;
    private final MembershipRepository membershipRepository;

    public List<Membership> getMemberships(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));

        return membershipRepository.findAllByMemberId(memberId);
    }

    public Membership getMembership(Long memberId, Long membershipId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버십입니다."));

        verify(member, membership);

        return membership;
    }

    public void verify(Member member, Membership membership) {
        if (!membership.getMember().equals(member)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }

    @Transactional
    public Membership register(Long memberId, String companyName) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));

        MembershipType membershipType = MembershipType.getType(companyName);
        if (membershipType == null) {
            throw new IllegalArgumentException("존재하지 않는 멤버십 이름입니다.");
        }
        if (membershipRepository.findByMemberIdAndMembershipType(memberId, membershipType).isPresent()) {
            throw new IllegalArgumentException("이미 등록한 멤버십입니다.");
        }

        Membership membership = Membership.builder()
                .member(member)
                .membershipType(membershipType)
                .build();
        return membershipRepository.save(membership);
    }

    @Transactional
    public void  delete(Long memberId, Long membershipId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버십입니다."));

        verify(member, membership);

        membershipRepository.delete(membership);
    }

    /**
     * 멤버십 포인트 적립 방식은 확장이 가능하게 설계한다.
     * <pre>
     *     - 현재 포인트 적립 비율은 결제 금액의 1% 고정
     *     - 추후 고정 금액(1000원)으로 확장이 가능하다.
     * </pre>
     */
    public void addPoint(Long memberId, Long membershipId, int amount) {
        throw new IllegalArgumentException();
    }
}
