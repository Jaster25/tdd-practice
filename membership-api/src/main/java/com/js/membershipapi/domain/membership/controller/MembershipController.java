package com.js.membershipapi.domain.membership.controller;

import com.js.membershipapi.domain.membership.dto.MembershipDetailedResponseDto;
import com.js.membershipapi.domain.membership.dto.MembershipRequestDto;
import com.js.membershipapi.domain.membership.dto.MembershipResponseDto;
import com.js.membershipapi.domain.membership.dto.PointRequestDto;
import com.js.membershipapi.domain.membership.entity.Membership;
import com.js.membershipapi.domain.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    private final String MEMBER_ID_HEADER = "X-USER-ID";

    @PostMapping("/api/v1/memberships")
    public ResponseEntity<MembershipResponseDto> registerMembershipApi(@RequestHeader(MEMBER_ID_HEADER) Long memberId,
                                                                       @RequestBody MembershipRequestDto requestDto) {
        Membership membership = membershipService.register(memberId, requestDto.getCompanyName(), requestDto.getPoint());

        MembershipResponseDto responseDto = MembershipResponseDto.builder()
                .membershipId(membership.getId())
                .companyName(membership.getMembershipType().getCompanyName())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/api/v1/memberships")
    public ResponseEntity<List<MembershipDetailedResponseDto>> getMembershipsApi(@RequestHeader(MEMBER_ID_HEADER) Long memberId) {
        List<Membership> memberships = membershipService.getMemberships(memberId);
        List<MembershipDetailedResponseDto> responseDto = memberships.stream()
                .map(MembershipDetailedResponseDto::of)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/api/v1/memberships/{membershipId}")
    public ResponseEntity<MembershipDetailedResponseDto> getMembershipApi(@RequestHeader(MEMBER_ID_HEADER) Long memberId,
                                                                          @PathVariable Long membershipId) {
        Membership membership = membershipService.getMembership(memberId, membershipId);
        MembershipDetailedResponseDto responseDto = MembershipDetailedResponseDto.of(membership);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/api/v1/memberships/{membershipId}")
    public ResponseEntity<Void> deleteMembershipApi(@RequestHeader(MEMBER_ID_HEADER) Long memberId,
                                                    @PathVariable Long membershipId) {
        membershipService.delete(memberId, membershipId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/api/v1/memberships/{membershipId}/add")
    public ResponseEntity<Void> addMembershipPointApi(@RequestHeader(MEMBER_ID_HEADER) Long memberId,
                                                      @PathVariable Long membershipId,
                                                      @RequestBody PointRequestDto requestDto) {
        membershipService.addPoint(memberId, membershipId, requestDto.getAmount());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
