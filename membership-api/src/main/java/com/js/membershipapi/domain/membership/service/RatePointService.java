package com.js.membershipapi.domain.membership.service;

import org.springframework.stereotype.Service;

@Service
public class RatePointService implements PointService {

    private final int RATE = 1;

    @Override
    public int calculate(int amount) {
        return amount * RATE / 100;
    }
}
