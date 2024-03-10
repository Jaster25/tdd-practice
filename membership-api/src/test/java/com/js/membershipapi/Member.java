package com.js.membershipapi;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Member {

    private int id;
    private String name;
    private final List<String> memberships;


    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.memberships = new ArrayList<>();
    }

    public Member(int id, String name, List<String> memberships) {
        this.id = id;
        this.name = name;
        this.memberships = memberships;
    }
}
