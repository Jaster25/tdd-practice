package com.js.membershipapi;

import com.js.membershipapi.domain.membership.repository.MembershipRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @InjectMocks
    private MembershipService membershipService;

    @Mock
    private MembershipRepository membershipRepository;

    @DisplayName("존재하지 않는 멤버의 멤버십 목록 조회")
    @Test
    void getMembershipsOfNonexistentMember() {
        // given
        Long memberId = 123L;
        
        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> membershipService.getMemberships(memberId));
    }

    // TODO: 멤버십 목록 조회

    // TODO: 멤버십 상세 조회
    // TODO: 멤버십 포인트 적립(적립 방식은 확장 가능하게)
}
