package com.codeit.springwebbasic.member.controller;

import com.codeit.springwebbasic.common.dto.ApiResponse;
import com.codeit.springwebbasic.member.dto.request.MemberCreateRequestDto;
import com.codeit.springwebbasic.member.dto.response.MemberResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// Swagger 전용 인터페이스를 하나 선언해서 비즈니스 로직과 문서화 로직 분리
// 기존 컨트롤러는 본연의 역할에 집중
@Tag(name = "사용자 관리(Member Controller)", description = "사용자 회원가입, 사용자 조회(단건, 다건) 등을 관리하는 API 입니다.")
public interface MemberControllerDocs {


    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "회원 가입 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                               {
                                                   "code": "SUCCESS",
                                                   "message": "요청에 성공 했습니다.",
                                                   "data": {
                                                       "id": 1,
                                                       "name": "youngble",
                                                       "email": "Youngble11@email.com",
                                                       "phone": "010-2222-2222",
                                                       "grade": null,
                                                       "joinedAt": "2025-11-07T10:39:20.064564"
                                                   }
                                               }
                                            """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청(이메일 중복, 유효성 검사 실패 등)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                               {
                                                   "code": "FAIL",
                                                   "message": "이미 존재하는 이메일입니다: Youngble11@email.com",
                                                   "data": null
                                               }
                                            """
                            )
                    )
            )
    })
    @Operation(
            summary = "회원가입",
            description = """
                     새로운 사용자를 등록합니다.
                    
                     ## 요청 데이터
                     - 이름, 이메일, 전화번호 정보가 필요합니다.
                     - 모든 정보는 필수값입니다.
                     - 이메일은 중복될 수 없습니다.
                    
                    ## 응답
                    - 성공 시 저장된 회원 정보가 반환됩니다.
                    """
    )
    ResponseEntity<ApiResponse<MemberResponseDto>> createMember(
            MemberCreateRequestDto requestDto
    );

    public MemberResponseDto findMemberById(Long id);


    // DTO(JSON)로 전달받는 데이터는 직접 클래스에 @Schema 적용
    // 낱개로 전달되는 데이터는 @Parameter로 설명 추가
    public ResponseEntity<ApiResponse<List<MemberResponseDto>>> findMembersByNameContaining(
            @Parameter(description = "회원 리스트 조회(이름)", required = false)
            String name
    );

}
