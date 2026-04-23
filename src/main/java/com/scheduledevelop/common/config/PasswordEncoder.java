package com.scheduledevelop.common.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * 비밀번호 암호화 및 검증을 담당하는 클래스입니다.
 *
 * 사용자의 비밀번호를 안전하게 저장하기 위해 암호화하며,
 * 로그인 시 입력한 비밀번호와 저장된 비밀번호를 비교하는 역할을 수행합니다.
 */
@Component
public class PasswordEncoder {

    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}