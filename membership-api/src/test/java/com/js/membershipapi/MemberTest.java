package com.js.membershipapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberTest {

    @DisplayName("멤버십 전체 조회")
    @Test
    void getMembershipList() {
        // given
        Membership membership1 = new Membership(1L, MembershipType.GSNPOINT);
        Membership membership2 = new Membership(2L, MembershipType.NAVER);

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
        Membership membership1 = new Membership(1L, MembershipType.GSNPOINT);

        // when
        Membership result = member1.registerMembership(membership1);

        // then
        assertEquals("GS&POINT", result.getMembershipType().getCompanyName());
        assertEquals(0, result.getPoint());
    }

    @DisplayName("존재하지 않는 멤버십 삭제")
    @Test
    void deleteNonexistentMembership() {
        // given
        Member member1 = new Member(1L, "김회원");
        Membership membership1 = new Membership(1L, MembershipType.GSNPOINT);

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> member1.deleteMembership(membership1));
    }

    @DisplayName("존재하는 멤버십 삭제")
    @Test
    void deleteMembership() {
        // given
        Member member1 = new Member(1L, "김회원");
        Membership membership1 = new Membership(1L, MembershipType.GSNPOINT);
        Membership membership2 = new Membership(2L, MembershipType.NAVER);
        member1.registerMembership(membership1);
        member1.registerMembership(membership2);

        // when
        member1.deleteMembership(membership2);

        // then
        assertEquals(1, member1.getMemberships().size());
        assertEquals(MembershipType.GSNPOINT, member1.getMemberships().get(0).getMembershipType());
    }
}
