package com.scheduledevelop.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 로그인 요청 데이터를 전달받기 위한 DTO입니다.
 *
 * 사용자가 로그인 시 입력한 이메일, 비밀번호 등의 인증 정보를 담습니다.
 */
@Getter
public class LoginRequest {

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 8글자 이상이어야 합니다.")
    private String password;
}
