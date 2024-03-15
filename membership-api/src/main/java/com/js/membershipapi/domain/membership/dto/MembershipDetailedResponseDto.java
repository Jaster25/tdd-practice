package com.js.membershipapi.domain.membership.dto;

import com.js.membershipapi.domain.membership.entity.Membership;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MembershipDetailedResponseDto {

    private final Long membershipId;
    private final String membershipName;
    private final int point;

    @Builder
    private MembershipDetailedResponseDto(Long membershipId, String membershipName, int point) {
        this.membershipId = membershipId;
        this.membershipName = membershipName;
        this.point = point;
    }

    public static MembershipDetailedResponseDto of(Membership membership) {
        return MembershipDetailedResponseDto.builder()
                .membershipId(membership.getId())
                .membershipName(membership.getMembershipType().getCompanyName())
                .point(membership.getPoint())
                .build();
    }
}
