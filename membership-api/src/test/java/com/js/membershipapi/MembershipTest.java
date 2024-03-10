package com.js.membershipapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MembershipTest {

    @DisplayName("포인트 조회")
    @Test
    void getPoint() {
        // given
        Membership membership = new Membership(1L, "GS&POINT", 500);

        // when
        int result = membership.getPoint();

        // then
        Assertions.assertEquals(500, result);
    }
}
