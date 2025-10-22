package com.codeit.springwebbasic.member.service;

import com.codeit.springwebbasic.member.dto.request.MemberCreateRequestDto;
import com.codeit.springwebbasic.member.entity.Member;
import com.codeit.springwebbasic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;
    // 회원 가입
    // url: /api/members, POST
    // 데이터: name: string(필수), email: string(필수), phone: string(필수, 전번 형식 검사 필요)
    // 요청 DTO: MemberCreateRequestDto
    // 비즈니스로직: 이메일 중복 체크 필요, DTO를 Entity로 변환해서 멤버 저장
    // 응답: id, name, email, phone, grade, joinedAt
    // 상태 코드: 201 CREATED

    public Member createMember(MemberCreateRequestDto requestDto) {
        // 이메일 중복체크, existsByEmail repo
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다: " + requestDto.getEmail());
        }
        return memberRepository.save(requestDto.toEntity());

    }


    // 회원 조회 (단일)
    // url: /api/members/{id}, GET
    // 비즈니스로직: 회원 조회후 리턴
    // 응답: 위에 사용한 Response용 DTO로 응답
    // 상태 코드:  200 OK, 회원 없을 시  "회원을 찾을 수 없습니다." , 400 BAD_REQUEST
    public Member getMember(Long id) {
       return memberRepository.findById(id).orElseThrow(() -> new IllegalStateException("회원을 찾을 수 없습니다."));
    }

    // 전체 회원 조회 & 검색
    // Url: /api/members?name={name}, GET | name은 전달되지 않을 수도 있습니다.
    // name이 전달되지 않는다면 전체조회, 전달된다면 name이 포함된(contains) 회원(List)을 조회.
    // 비즈니스로직: 각 상황에 맞는 Service 메서드를 호출해서 리턴
    // 응답: 조회된 회원(ResponseDto)을 리스트에 담아서 리턴 | 200 OK
    public List<Member> getMembersByName(String name) {
        return memberRepository.findByNameContaining(name);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

}
