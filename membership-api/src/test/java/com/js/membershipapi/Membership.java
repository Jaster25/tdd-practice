package com.js.membershipapi;

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

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    private int point = 0;

    @CreatedDate
    private LocalDateTime registeredAt;

    public Membership(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public void addPoint(int earnedPoint) {
        point += earnedPoint;
    }
}
