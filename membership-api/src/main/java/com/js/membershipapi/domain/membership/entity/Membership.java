package com.js.membershipapi.domain.membership.entity;

import com.js.membershipapi.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    private int point = 0;

    @CreatedDate
    private LocalDateTime registeredAt;

    public Membership(Member member, MembershipType membershipType) {
        this.member = member;
        this.membershipType = membershipType;
    }

    public void addPoint(int earnedPoint) {
        point += earnedPoint;
    }
}
