package com.js.membershipapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberTest {

    @DisplayName("사용자 멤버십 전체 조회")
    @Test
    void getMembershipList() {
        // given
        Member member1 = new Member(1, "김회원");
        Member member2 = new Member(2, "이회원", List.of("GS&POINT", "KAKAO"));

        // when
        List<String> result1 = member1.getMemberships();
        List<String> result2 = member2.getMemberships();

        // then
        Assertions.assertEquals(0, result1.size());
        Assertions.assertEquals(2, result2.size());
    }
}
