package com.js.membershipapi;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipResponseDto {

    private Long membershipId;
    private String companyName;

    @Builder
    public MembershipResponseDto(Long membershipId, String companyName) {
        this.membershipId = membershipId;
        this.companyName = companyName;
    }
}
