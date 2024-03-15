package com.js.membershipapi;

import com.google.gson.Gson;
import com.js.membershipapi.domain.member.entity.Member;
import com.js.membershipapi.domain.membership.entity.Membership;
import com.js.membershipapi.domain.membership.entity.MembershipType;
import com.js.membershipapi.domain.membership.service.MembershipService;
import com.js.membershipapi.global.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MembershipControllerTest {

    @InjectMocks
    private MembershipController membershipController;

    @Mock
    private MembershipService membershipService;

    private final String MEMBER_ID_HEADER = "X-USER-ID";

    private MockMvc mockMvc;
    private Gson gson;


    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(membershipController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        gson = new Gson();
    }

    @DisplayName("존재하지 않는 멤버의 멤버십 등록")
    @Test
    public void registerNonexistentMember() throws Exception {
        // given
        final String URL = "/api/v1/memberships";
        MembershipRequestDto MembershipRequestDto = new MembershipRequestDto(MembershipType.GSNPOINT.getCompanyName(), 500);
        given(membershipService.register(anyLong(), anyString(), anyInt()))
                .willThrow(IllegalArgumentException.class);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .header(MEMBER_ID_HEADER, 1L)
                        .content(gson.toJson(MembershipRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("존재하지 않는 회사명 멤버십 등록")
    @Test
    public void registerNonexistentCompanyName() throws Exception {
        // given
        final String URL = "/api/v1/memberships";
        MembershipRequestDto MembershipRequestDto = new MembershipRequestDto("aBC", 500);
        given(membershipService.register(anyLong(), anyString(), anyInt()))
                .willThrow(IllegalArgumentException.class);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .header(MEMBER_ID_HEADER, 1L)
                        .content(gson.toJson(MembershipRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("멤버십 등록")
    @Test
    public void register() throws Exception {
        // given
        final String URL = "/api/v1/memberships";
        MembershipRequestDto membershipRequestDto = new MembershipRequestDto(MembershipType.GSNPOINT.getCompanyName(), 500);

        Member member = Member.builder()
                .name("김회원")
                .build();
        Membership membership = Membership.builder()
                .id(123L)
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .build();
        given(membershipService.register(anyLong(), anyString(), anyInt()))
                .willReturn(membership);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .header(MEMBER_ID_HEADER, 1L)
                        .content(gson.toJson(membershipRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isCreated());
    }
    
    @DisplayName("존재하지 않는 멤버의 멤버십 전체 조회")
    @Test
    void getMembershipsOfNonexistentMember() throws Exception {
        // given
        final String URL = "/api/v1/memberships";
        MembershipRequestDto MembershipRequestDto = new MembershipRequestDto("aBC", 500);
        given(membershipService.register(anyLong(), anyString(), anyInt()))
                .willThrow(IllegalArgumentException.class);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(URL)
                        .header(MEMBER_ID_HEADER, 1L)
                        .content(gson.toJson(MembershipRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        
        // then
        resultActions.andExpect(status().isBadRequest());
    }
}
