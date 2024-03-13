package com.js.membershipapi;

import com.js.membershipapi.domain.member.entity.Member;
import com.js.membershipapi.domain.member.repository.MemberRepository;
import com.js.membershipapi.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @DisplayName("존재하지 않는 멤버 조회")
    @Test
    void getNonexistentMember() {
        // given
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> memberService.getMember(123L));
    }

    @DisplayName("멤버 조회")
    @Test
    void getMember() {
        // given
        Member member1 = Member.builder()
                .name("김회원")
                .build();
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member1));

        // when
        Member foundMember = memberService.getMember(123L);

        // then
        assertEquals("김회원", foundMember.getName());
    }
}
