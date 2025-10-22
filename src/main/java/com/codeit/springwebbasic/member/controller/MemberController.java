package com.codeit.springwebbasic.member.controller;

import com.codeit.springwebbasic.member.dto.request.MemberCreateRequestDto;
import com.codeit.springwebbasic.member.dto.response.MemberResponseDto;
import com.codeit.springwebbasic.member.entity.Member;
import com.codeit.springwebbasic.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor

public class MemberController {

    private final MemberService memberService;


    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@Valid @RequestBody MemberCreateRequestDto requestDto) {
        Member member = memberService.createMember(requestDto);
        return new ResponseEntity<>(MemberResponseDto.from(member), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public MemberResponseDto findMemberById(@PathVariable Long id) {
        Member member = memberService.getMember(id);
        return MemberResponseDto.from(member);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findMembersByNameContaining(@RequestParam(required = false) String name) {

        if (name == null) {
            return ResponseEntity.ok(memberService.getAllMembers()
                    .stream()
                    .map(MemberResponseDto::from)
                    .toList());
        } else {
            return ResponseEntity.ok(memberService.getMembersByName(name)
                    .stream()
                    .map(MemberResponseDto::from)
                    .toList());

        }

    }
}
