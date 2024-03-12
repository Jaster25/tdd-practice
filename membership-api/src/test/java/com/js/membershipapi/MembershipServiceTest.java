package com.js.membershipapi;

import com.js.membershipapi.domain.member.entity.Member;
import com.js.membershipapi.domain.membership.entity.Membership;
import com.js.membershipapi.domain.membership.entity.MembershipType;
import com.js.membershipapi.domain.membership.repository.MembershipRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @InjectMocks
    private MembershipService membershipService;

    @Mock
    private MembershipRepository membershipRepository;

    @DisplayName("존재하지 않는 멤버의 멤버십 목록 조회")
    @Test
    void getMembershipsOfNonexistentMember() {
        // given
        Long memberId = 123L;
        
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

        BDDMockito.given(membershipRepository.findAllByMemberId(memberId))
                .willReturn(List.of(membership1, membership2));

        // when
        List<Membership> foundMemberships = membershipService.getMemberships(memberId);

        // then
        assertEquals(2, foundMemberships.size());
    }

    // TODO: 멤버십 상세 조회
    // TODO: 멤버십 포인트 적립(적립 방식은 확장 가능하게)
}
