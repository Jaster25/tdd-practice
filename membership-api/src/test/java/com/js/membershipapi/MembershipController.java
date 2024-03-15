package com.js.membershipapi;

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
}
