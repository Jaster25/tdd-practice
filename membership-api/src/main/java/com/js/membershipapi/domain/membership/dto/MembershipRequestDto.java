package com.js.membershipapi.domain.membership.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipRequestDto {

    private String companyName;
    private int point;

    public MembershipRequestDto(String companyName, int point) {
        this.companyName = companyName;
        this.point = point;
    }
}
