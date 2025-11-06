package com.codeit.springwebbasic.rental.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentalRequestDto {
    // client에서는 온전한 Member, Book의 정보를 넘겨줄 필요없이 고유 식벽자 값인 id만 넘겨주면됨, id를 가지고 조회해서 완전한 Member, Book을 저장하거나 수정하면됨
    // 문자열이 아닌 정수 타입에는 NotNull만 가능
    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;
    @NotNull(message = "도서 ID는 필수입니다.")
    private Long bookId;
}
