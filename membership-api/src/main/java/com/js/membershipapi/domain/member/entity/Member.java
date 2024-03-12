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


    // TODO: 멤버십 등록 기능은 서비스 계층에서 작업해야 식별자 생성과 영속화가 가능하다.
//    public Membership registerMembership(Membership newMembership) {
//        memberships.add(newMembership);
//        return newMembership;
//    }

    public void deleteMembership(Membership membership) {
        if (!memberships.contains(membership)) {
            throw new IllegalArgumentException("해당 회원의 멤버십이 아닙니다.");
        }
        memberships.remove(membership);
    }
}
