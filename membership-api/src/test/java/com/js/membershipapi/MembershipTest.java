package com.js.membershipapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MembershipTest {

    @DisplayName("포인트 조회")
    @Test
    void getPoint() {
        // given
        Membership membership = new Membership(MembershipType.GSNPOINT);

        // when
        int result = membership.getPoint();

        // then
        assertEquals(0, result);
    }
    
    @DisplayName("포인트 추가")
    @Test
    void addPoint() {
        // given
        Membership membership = new Membership(MembershipType.GSNPOINT);
        
        // when
        membership.addPoint(300);

        // then
        assertEquals(300, membership.getPoint());
    }
}
