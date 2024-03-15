package com.js.membershipapi;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointRequestDto {

    private int amount;

    public PointRequestDto(int amount) {
        this.amount = amount;
    }
}
