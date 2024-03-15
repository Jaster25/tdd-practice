package com.js.membershipapi.domain.membership.entity;

import com.js.membershipapi.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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


    @Builder
    public Membership(Long id, Member member, MembershipType membershipType, int point, LocalDateTime registeredAt) {
        this.id = id;
        this.member = member;
        this.membershipType = membershipType;
        this.point = point;
        this.registeredAt = registeredAt;
    }


    public void addPoint(int earnedPoint) {
        if (earnedPoint < 0) {
            throw new IllegalArgumentException("추가하려는 포인트는 음수일 수 없습니다.");
        }
        point += earnedPoint;
    }
}
