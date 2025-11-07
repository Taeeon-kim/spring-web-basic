package com.codeit.springwebbasic.common.dto;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    // 1. 공통 비즈니스 상태 코드
    // "SUCCESS" ,"USER_NOT_FOUND", "INVALID_INPUT" -> enum 사용해도 괜춚
    private final String code;

    // 2. 비즈니스 상태 메세지(상태 코드보다 좀더 상세한 결과 보고)
    private final String message;

    // 3. 실제 응답하고자 하는 데이터
    private final T data;

    // 생성자를 private으로 막으세요.( 정적 펙토리 메서드 사용 강제)
    private ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 정적 팩토리 메서드 추가
    // 객체를 생성할 때 생성자호출이 아닌 static 메서드로 간접적으로 생성자를 호출하는 행위
    // 일관된 객체 생성이 가능하고, 생성자를 호출하는 것보다 메서드 이름으로 좀 더 목적을 명확하게 할 수 있다.

    /*
     1. 성공 응답(데이터 포함)
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("SUCCESS", "요청에 성공 했습니다.", data);
    }

    /*
    2. 성공 응답(데이터 없음)
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>("SUCCESS", "요청에 성공 했습니다.", null);
    }

    /*
    3. 실패 응답(커스텀 코드와 메세지)
     */
    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }

}
