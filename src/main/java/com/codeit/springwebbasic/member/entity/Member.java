package com.codeit.springwebbasic.member.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private MemberGrade grade;
    private LocalDateTime joinedAt;

    // 등급 업그레이드 (기존 등급에서 한단계 업그레이드)
    public void upgradeGrade(){
        this.grade = this.grade.upgrade();
    }

}
