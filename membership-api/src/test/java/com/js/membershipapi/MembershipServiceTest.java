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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @InjectMocks
    private MembershipService membershipService;

    @Mock
    private PointService ratePointService;
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
        Member member = Member.builder()
                .name("김회원")
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.getMembership(123L, 123L));
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
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));
        given(membershipRepository.findById(anyLong()))
                .willReturn(Optional.of(membership1));

        // when
        Membership foundMembership = membershipService.getMembership(1L, 1L);

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

    @DisplayName("본인 멤버십 검증")
    @Test
    void verifyAuthorizedMember() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        Membership membership = Membership.builder()
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .build();

        // when
        membershipService.verify(member, membership);

        // then
    }

    @DisplayName("존재하지 않는 멤버 멤버십 등록")
    @Test
    void registerNonexistentMember() {
        // given
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.register(1L, MembershipType.GSNPOINT.getCompanyName()));
    }

    @DisplayName("존재하지 않는 멤버십 이름 등록")
    @Test
    void registerNonexistentMembershipName() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.register(1L, "abcd"));
    }

    @DisplayName("이미 존재하는 멤버십 중복 등록")
    @Test
    void registerDuplicatedMembership() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        Membership membership = Membership.builder()
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));
        given(membershipRepository.findByMemberIdAndMembershipType(anyLong(), any()))
                .willReturn(Optional.of(membership));

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.register(1L, MembershipType.GSNPOINT.getCompanyName()));
    }

    @DisplayName("존재하지 않는 멤버의 멤버십 삭제")
    @Test
    void deleteMembershipOfNonexistentMember() {
        // given
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.delete(1L, 5L));
    }

    @DisplayName("존재하지 않는 멤버십 삭제")
    @Test
    void deleteNonexistentMembership() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));
        given(membershipRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.delete(1L, 5L));
    }

    @DisplayName("권한 없는 멤버십 삭제")
    @Test
    void deleteNonauthorizedMembership() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        Member member2 = Member.builder()
                .name("이회원")
                .build();
        Membership membership = Membership.builder()
                .member(member2)
                .membershipType(MembershipType.GSNPOINT)
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));
        given(membershipRepository.findById(anyLong()))
                .willReturn(Optional.of(membership));

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.delete(1L, 5L));
    }

    @DisplayName("멤버십 삭제")
    @Test
    void deleteMembership() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        Membership membership = Membership.builder()
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));
        given(membershipRepository.findById(anyLong()))
                .willReturn(Optional.of(membership));

        // when
        membershipService.delete(1L, 2L);

        // then
        assertEquals(0, member.getMemberships().size());
    }

    @DisplayName("존재하지 않는 멤버 포인트 적립")
    @Test
    void addPointToNonexistentMember() {
        // given
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.addPoint(1L, 1L, 3000));
    }

    @DisplayName("존재하지 않는 멤버십 포인트 적립")
    @Test
    void addPointToNonexistentMembership() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));
        given(membershipRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.addPoint(1L, 1L, 3000));
    }

    @DisplayName("권한 없는 멤버십 포인트 적립")
    @Test
    void addPointToNonauthorizedMembership() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        Member member2 = Member.builder()
                .name("이회원")
                .build();
        Membership membership = Membership.builder()
                .member(member2)
                .membershipType(MembershipType.GSNPOINT)
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));
        given(membershipRepository.findById(anyLong()))
                .willReturn(Optional.of(membership));

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.addPoint(1L, 2L, 3000));
    }

    @DisplayName("멤버십 포인트 적립")
    @Test
    void addPoint() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        Membership membership = Membership.builder()
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));
        given(membershipRepository.findById(anyLong()))
                .willReturn(Optional.of(membership));
        given(ratePointService.calculate(anyInt()))
                .willReturn(2);

        // when
        membershipService.addPoint(1L, 2L, 200);
        membershipService.addPoint(1L, 2L, 200);

        // then
        assertEquals(4, membership.getPoint());
    }
}
