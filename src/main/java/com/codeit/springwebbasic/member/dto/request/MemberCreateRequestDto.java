package com.codeit.springwebbasic.member.dto.request;

import com.codeit.springwebbasic.member.entity.Member;
import com.codeit.springwebbasic.member.entity.MemberGrade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

// record: java 16+, 불변 데이터 운반 객체를 아주 간결하게 작성할 수 있음
// getter는 기본 제공되는데, 이름에 get이 붙지 않습니다.
// Lombok의 builder 사용 가능 (목적에 맞게 사용해야한다.)
public record MemberCreateRequestDto(
        Long id,
        @NotBlank(message = "이름은 필수입니다.")
        String name,
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message ="올바른 이메일 형식이 아닙니다.")
        String email,
        @NotBlank(message ="전화번호는 필수입니다")
        String phone,
        MemberGrade grade,
        LocalDateTime joinedAt
) {

    public Member toEntity(){
        return Member.builder()
                .joinedAt(LocalDateTime.now())
                .phone(this.phone)
                .grade(this.grade)
                .email(this.email)
                .id(this.id)
                .name(this.name)
                .build();
    }

}


