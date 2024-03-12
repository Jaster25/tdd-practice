package com.js.membershipapi;

import com.js.membershipapi.domain.member.entity.Member;
import com.js.membershipapi.domain.member.repository.MemberRepository;
import com.js.membershipapi.domain.membership.entity.Membership;
import com.js.membershipapi.domain.membership.entity.MembershipType;
import com.js.membershipapi.domain.membership.repository.MembershipRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @InjectMocks
    private MembershipService membershipService;

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MembershipRepository membershipRepository;

    @DisplayName("존재하지 않는 멤버의 멤버십 목록 조회")
    @Test
    void getMembershipsOfNonexistentMember() {
        // given
        Long memberId = 123L;
        given(memberRepository.findById(123L))
                .willThrow(IllegalArgumentException.class);

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.getMemberships(memberId));
    }

    @DisplayName("멤버십 목록 조회")
    @Test
    void getMemberships() {
        // given
        Long memberId = 123L;
        Member member = Member.builder()
                .id(memberId)
                .name("김회원")
                .build();
        Membership membership1 = Membership.builder()
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .build();
        Membership membership2 = Membership.builder()
                .member(member)
                .membershipType(MembershipType.KAKAO)
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));
        given(membershipRepository.findAllByMemberId(memberId))
                .willReturn(List.of(membership1, membership2));

        // when
        List<Membership> foundMemberships = membershipService.getMemberships(memberId);

        // then
        assertEquals(2, foundMemberships.size());
    }

    @DisplayName("존재하지 않는 멤버십 상세 조회")
    @Test
    void getNonexistentMembership() {
        // given
        given(membershipRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.getMembership(123L));
    }

    @DisplayName("멤버십 상세 조회")
    @Test
    void getMembership() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        Membership membership1 = Membership.builder()
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .build();
        given(membershipRepository.findById(anyLong()))
                .willReturn(Optional.of(membership1));

        // when
        Membership foundMembership = membershipService.getMembership(anyLong());

        // then
        assertEquals(member.getName(), foundMembership.getMember().getName());
        assertEquals(membership1.getMembershipType(), foundMembership.getMembershipType());
    }

    @DisplayName("본인 멤버십이 아닌 경우 검증")
    @Test
    void verifyNonauthorizedMember() {
        // given
        Member member1 = Member.builder()
                .name("김회원")
                .build();
        Member member2 = Member.builder()
                .name("임회원")
                .build();
        Membership membership = Membership.builder()
                .member(member2)
                .membershipType(MembershipType.GSNPOINT)
                .build();

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.verify(member1, membership));
    }
    // TODO: 본인 멤버십 검증

    // TODO: 멤버십 포인트 적립(적립 방식은 확장 가능하게)
    // TODO: 멤버십 등록
    // TODO: 멤버십 삭제
}
