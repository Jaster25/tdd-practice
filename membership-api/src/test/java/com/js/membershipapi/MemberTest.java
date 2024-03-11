package com.js.membershipapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberTest {

    @DisplayName("멤버십 전체 조회")
    @Test
    void getMembershipList() {
        // given
        Membership membership1 = new Membership(1L, "GS&POINT");
        Membership membership2 = new Membership(2L, "NAVER");

        Member member1 = new Member(1L, "김회원");
        Member member2 = new Member(2L, "이회원", List.of(membership1));
        Member member3 = new Member(3L, "박회원", List.of(membership1, membership2));

        // when
        List<Membership> result1 = member1.getMemberships();
        List<Membership> result2 = member2.getMemberships();
        List<Membership> result3 = member3.getMemberships();

        // then
        assertEquals(0, result1.size());
        assertEquals(1, result2.size());
        assertEquals(2, result3.size());
    }

    @DisplayName("멤버십 등록")
    @Test
    void registerMembership() {
        // given
        Member member1 = new Member(1L, "김회원");
        Membership membership1 = new Membership(1L, "GS&POINT");

        // when
        Membership result = member1.registerMembership(membership1);

        // then
        assertEquals("GS&POINT", result.getName());
        assertEquals(0, result.getPoint());
    }
}
