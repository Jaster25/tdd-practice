package com.js.membershipapi;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipRequestDto {

    private String membershipName;
    private int point;

    public MembershipRequestDto(String membershipName, int point) {
        this.membershipName = membershipName;
        this.point = point;
    }
}
