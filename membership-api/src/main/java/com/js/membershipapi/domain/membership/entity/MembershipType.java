package com.js.membershipapi.domain.membership.entity;

import lombok.Getter;

@Getter
public enum MembershipType {

    GSNPOINT("GS"),
    KAKAO("KAKAO"),
    NAVER("NAVER"),
    ;

    private final String companyName;

    MembershipType(String companyName) {
        this.companyName = companyName;
    }

    public static MembershipType getType(String companyName) {
        for (MembershipType type : MembershipType.values()) {
            if (type.getCompanyName().equals(companyName)) {
                return type;
            }
        }
        return null;
    }
}
