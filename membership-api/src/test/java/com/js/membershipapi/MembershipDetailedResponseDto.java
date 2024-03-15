package com.js.membershipapi;

import com.js.membershipapi.domain.membership.entity.Membership;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MembershipDetailedResponseDto {

    private final Long membershipId;
    private final String membershipName;
    private final int point;
    private final LocalDateTime registeredAt;

    @Builder
    private MembershipDetailedResponseDto(Long membershipId, String membershipName, int point, LocalDateTime registeredAt) {
        this.membershipId = membershipId;
        this.membershipName = membershipName;
        this.point = point;
        this.registeredAt = registeredAt;
    }

    public static MembershipDetailedResponseDto of(Membership membership) {
        return MembershipDetailedResponseDto.builder()
                .membershipId(membership.getId())
                .membershipName(membership.getMembershipType().getCompanyName())
                .point(membership.getPoint())
                .registeredAt(membership.getRegisteredAt())
                .build();
    }
}
