package com.js.membershipapi.domain.member.entity;

import com.js.membershipapi.domain.membership.entity.Membership;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Membership> memberships = new ArrayList<>();


    @Builder
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
