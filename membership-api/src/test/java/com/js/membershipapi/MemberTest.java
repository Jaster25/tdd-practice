package com.js.membershipapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberTest {

    @DisplayName("멤버십 전체 조회")
    @Test
    void getMembershipList() {
        // given
        Member member1 = new Member(1L, "김회원");
        Member member2 = new Member(2L, "이회원", List.of("GS&POINT", "KAKAO"));

        // when
        List<String> result1 = member1.getMemberships();
        List<String> result2 = member2.getMemberships();

        // then
        Assertions.assertEquals(0, result1.size());
        Assertions.assertEquals(2, result2.size());
    }

    @DisplayName("멤버십 등록")
    @Test
    void registerMembership() {
        // given
        Member member1 = new Member(1L, "김회원");

        // when
        member1.registerMembership("GS&POINT");
        String result = member1.getMemberships().get(0);

        // then
        Assertions.assertEquals("GS&POINT", result);
    }
}
