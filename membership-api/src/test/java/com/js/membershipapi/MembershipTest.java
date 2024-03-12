package com.js.membershipapi;

import com.js.membershipapi.domain.member.entity.Member;
import com.js.membershipapi.domain.membership.entity.Membership;
import com.js.membershipapi.domain.membership.entity.MembershipType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MembershipTest {

    @DisplayName("포인트 조회")
    @Test
    void getPoint() {
        // given
        Member member = new Member("김회원");
        Membership membership = new Membership(member, MembershipType.GSNPOINT);

        // when
        int result = membership.getPoint();

        // then
        assertEquals(0, result);
    }
    
    @DisplayName("포인트 추가")
    @Test
    void addPoint() {
        // given
        Member member1 = new Member("김회원");
        Membership membership1 = new Membership(member1, MembershipType.GSNPOINT);

        Member member2 = new Member("이회원");
        Membership membership2 = new Membership(member2, MembershipType.GSNPOINT);

        // when
        membership1.addPoint(300);
        membership2.addPoint(100);
        membership2.addPoint(600);

        // then
        assertEquals(300, membership1.getPoint());
        assertEquals(700, membership2.getPoint());
    }
}
