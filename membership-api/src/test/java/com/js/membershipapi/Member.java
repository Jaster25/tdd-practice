package com.js.membershipapi;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Member {

    private Long id;
    private String name;
    private final List<Membership> memberships;

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
        this.memberships = new ArrayList<>();
    }

    public Member(Long id, String name, List<Membership> memberships) {
        this.id = id;
        this.name = name;
        this.memberships = memberships;
    }

    public Membership registerMembership(Membership newMembership) {
        memberships.add(newMembership);
        return newMembership;
    }

    public void deleteMembership(Membership membership) {
        if (!memberships.contains(membership)) {
            throw new IllegalArgumentException("해당 회원의 멤버십이 아닙니다.");
        }
        memberships.remove(membership);
    }
}
