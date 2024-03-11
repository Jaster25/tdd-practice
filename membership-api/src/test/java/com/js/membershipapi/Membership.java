package com.js.membershipapi;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Membership {

    private Long id;

    @Enumerated(EnumType.STRING)
    private final MembershipType membershipType;

    private int point = 0;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Membership(Long id, MembershipType membershipType) {
        this.id = id;
        this.membershipType = membershipType;
    }

    public void addPoint(int earnedPoint) {
        point += earnedPoint;
    }
}
