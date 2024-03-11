package com.js.membershipapi;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Membership {

    private Long id;
    private String name;
    private int point = 0;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Membership(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addPoint(int earnedPoint) {
    }
}
