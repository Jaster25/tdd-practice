package com.js.membershipapi;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Member {

    private Long id;
    private String name;
    private final List<String> memberships;

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
        this.memberships = new ArrayList<>();
    }

    public Member(Long id, String name, List<String> memberships) {
        this.id = id;
        this.name = name;
        this.memberships = memberships;
    }

    public String registerMembership(String membershipName) {
        memberships.add(membershipName);
        return membershipName;
    }
}
