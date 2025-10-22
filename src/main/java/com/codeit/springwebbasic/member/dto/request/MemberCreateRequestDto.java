package com.codeit.springwebbasic.member.dto.request;

import com.codeit.springwebbasic.member.entity.Member;
import com.codeit.springwebbasic.member.entity.MemberGrade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberCreateRequestDto {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^(?:\\+82[-\\s]?10|010)(?:\\d{8}|(?:([-\\s])\\d{4}\\1\\d{4}))$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phone;


    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .grade(MemberGrade.BRONZE)
                .joinedAt(LocalDateTime.now())
                .build();
    }

}
