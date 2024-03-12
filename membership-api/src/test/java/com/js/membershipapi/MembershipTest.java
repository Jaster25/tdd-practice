package com.js.membershipapi;

import com.js.membershipapi.domain.member.entity.Member;
import com.js.membershipapi.domain.membership.entity.Membership;
import com.js.membershipapi.domain.membership.entity.MembershipType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MembershipTest {

    @DisplayName("포인트 조회")
    @Test
    void getPoint() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        Membership membership = Membership.builder()
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .build();

        // when
        int result = membership.getPoint();

        // then
        assertEquals(0, result);
    }
    
    @DisplayName("마이너스 포인트 추가")
    @Test
    void addMinusPoint() {
        // given
        Member member = Member.builder()
                .name("김회원")
                .build();
        Membership membership = Membership.builder()
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .build();

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membership.addPoint(-500));
    }

    @DisplayName("포인트 추가")
    @Test
    void addPoint() {
        // given
        Member member1 = Member.builder()
                .name("김회원")
                .build();
        Membership membership1 = Membership.builder()
                .member(member1)
                .membershipType(MembershipType.GSNPOINT)
                .build();
        Member member2 = Member.builder()
                .name("이회원")
                .build();
        Membership membership2 = Membership.builder()
                .member(member2)
                .membershipType(MembershipType.GSNPOINT)
                .build();

        // when
        membership1.addPoint(300);
        membership2.addPoint(100);
        membership2.addPoint(600);

        // then
        assertEquals(300, membership1.getPoint());
        assertEquals(700, membership2.getPoint());
    }
}
