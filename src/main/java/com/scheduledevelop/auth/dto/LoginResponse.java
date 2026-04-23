package com.scheduledevelop.auth.dto;

import lombok.Getter;

/**
 * 로그인 처리 결과를 클라이언트에 반환하기 위한 DTO입니다.
 *
 * 로그인 성공 여부, 사용자 식별 정보, 메시지 등을 응답하는 데 사용됩니다.
 */
@Getter
public class LoginResponse {

    private final Long id;
    private final String email;
    private final String userName;

    public LoginResponse(Long id, String email, String userName) {
        this.id = id;
        this.email = email;
        this.userName = userName;
    }
}
