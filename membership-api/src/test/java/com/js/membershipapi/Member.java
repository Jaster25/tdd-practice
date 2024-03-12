package com.js.membershipapi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private List<Membership> memberships;

    public Member(String name) {
        this.name = name;
        this.memberships = new ArrayList<>();
    }

    public Member(String name, List<Membership> memberships) {
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
