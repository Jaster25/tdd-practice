package com.js.membershipapi;

public class Membership {

    private Long id;
    private String name;
    private int point;

    public Membership(Long id, String name) {
        this.id = id;
        this.name = name;
        this.point = 0;
    }

    public Membership(Long id, String name, int point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
