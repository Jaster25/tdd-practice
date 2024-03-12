package com.js.membershipapi.domain.membership.entity;

import lombok.Getter;

@Getter
public enum MembershipType {

    GSNPOINT("GS&POINT"),
    KAKAO("KAKAO"),
    NAVER("NAVER"),
    ;

    private final String companyName;

    MembershipType(String companyName) {
        this.companyName = companyName;
    }
}
