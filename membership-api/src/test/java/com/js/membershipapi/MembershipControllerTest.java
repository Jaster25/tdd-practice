package com.js.membershipapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.js.membershipapi.domain.member.entity.Member;
import com.js.membershipapi.domain.membership.entity.Membership;
import com.js.membershipapi.domain.membership.entity.MembershipType;
import com.js.membershipapi.domain.membership.service.MembershipService;
import com.js.membershipapi.global.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
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

        given(membershipService.getMemberships(anyLong()))
                .willThrow(IllegalArgumentException.class);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(URL)
                        .header(MEMBER_ID_HEADER, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        
        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("멤버십 전체 조회")
    @Test
    void getMemberships() throws Exception {
        // given
        final String URL = "/api/v1/memberships";

        Member member = Member.builder()
                .name("김회원")
                .build();
        Membership membership1 = Membership.builder()
                .id(1L)
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .build();
        Membership membership2 = Membership.builder()
                .id(2L)
                .member(member)
                .membershipType(MembershipType.KAKAO)
                .registeredAt(LocalDateTime.now())
                .build();
        given(membershipService.getMemberships(anyLong()))
                .willReturn(List.of(membership1, membership2));

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(URL)
                        .header(MEMBER_ID_HEADER, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk());

        Type listType = new TypeToken<ArrayList<MembershipDetailedResponseDto>>(){}.getType();
        List<MembershipDetailedResponseDto> responseDto = gson.fromJson(resultActions.andReturn()
                .getResponse().getContentAsString(StandardCharsets.UTF_8), listType);

        assertEquals(2, responseDto.size());
        assertEquals(MembershipType.GSNPOINT.getCompanyName(), responseDto.get(0).getMembershipName());
    }

    @DisplayName("존재하지 않는 멤버의 멤버십 상세 조회")
    @Test
    void getDetailedMembershipOfNonexistentMember() throws Exception {
        // given
        final String URL = "/api/v1/memberships/3";

        given(membershipService.getMembership(anyLong(), anyLong()))
                .willThrow(IllegalArgumentException.class);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(URL)
                        .header(MEMBER_ID_HEADER, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("멤버십 상세 조회")
    @Test
    void getDetailedMembership() throws Exception {
        // given
        final String URL = "/api/v1/memberships/3";

        Member member = Member.builder()
                .name("김회원")
                .build();
        Membership membership = Membership.builder()
                .id(3L)
                .member(member)
                .membershipType(MembershipType.GSNPOINT)
                .point(300)
                .build();
        given(membershipService.getMembership(anyLong(), anyLong()))
                .willReturn(membership);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(URL)
                        .header(MEMBER_ID_HEADER, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk());

        MembershipDetailedResponseDto responseDto = gson.fromJson(resultActions.andReturn()
                .getResponse().getContentAsString(StandardCharsets.UTF_8), MembershipDetailedResponseDto.class);

        assertEquals(3L, responseDto.getMembershipId());
        assertEquals(MembershipType.GSNPOINT.getCompanyName(), responseDto.getMembershipName());
    }

    @DisplayName("존재하지 않는 멤버의 멤버십 삭제")
    @Test
    void deleteMembershipOfNonexistentMember() throws Exception {
        // given
        final String URL = "/api/v1/memberships/3";

        doThrow(IllegalArgumentException.class)
                .when(membershipService)
                .delete(anyLong(), anyLong());

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete(URL)
                        .header(MEMBER_ID_HEADER, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("멤버십 삭제")
    @Test
    void deleteMembership() throws Exception {
        // given
        final String URL = "/api/v1/memberships/3";

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete(URL)
                        .header(MEMBER_ID_HEADER, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isNoContent());
    }
}
