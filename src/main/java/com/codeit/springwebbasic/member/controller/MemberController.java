package com.codeit.springwebbasic.member.controller;

import com.codeit.springwebbasic.common.dto.ApiResponse;
import com.codeit.springwebbasic.member.dto.request.MemberCreateRequestDto;
import com.codeit.springwebbasic.member.dto.response.MemberResponseDto;
import com.codeit.springwebbasic.member.entity.Member;
import com.codeit.springwebbasic.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Slf4j // log라는 이름의 Logger 객체를 바로 생성
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;


    // 회원가입
    // url: /api/members: POST
    // 데이터: name: string(필수), email: string(필수), phone: string(필수, 전번 형식 검삭 필요)
    // 요청 DTO: MemberCreateRequestDto

    @PostMapping
    public ResponseEntity<ApiResponse<MemberResponseDto>> createMember(
            @Parameter(description = "회원 가입 정보", required = true)
            @Valid @RequestBody MemberCreateRequestDto requestDto
    ) {
        log.info("/api/v1/members: POST, dto: {}", requestDto); // 로깅 횡단관심사

        MemberResponseDto responseDto = memberService.createMember(requestDto);

        ApiResponse<MemberResponseDto> response = ApiResponse.success(responseDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public MemberResponseDto findMemberById(@PathVariable Long id) {
        log.info("/api/v1/members/{}: GET", id);
        return memberService.getMember(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberResponseDto>>> findMembersByNameContaining(@RequestParam(required = false) String name) {

        if (name == null) {
            return ResponseEntity.ok(ApiResponse.success(memberService.getAllMembers()));
        } else {
            return ResponseEntity.ok(ApiResponse.success(memberService.getMembersByName(name)));
        }

    }
}
