package com.scheduledevelop.auth.service;

import com.scheduledevelop.auth.dto.LoginRequest;
import com.scheduledevelop.auth.dto.LoginResponse;
import com.scheduledevelop.auth.dto.SessionUser;
import com.scheduledevelop.common.config.PasswordEncoder;
import com.scheduledevelop.user.entity.User;
import com.scheduledevelop.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 인증 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 *
 * 사용자의 로그인 검증, 비밀번호 확인, 인증 정보 처리 등
 * 인증과 관련된 핵심 로직을 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LoginResponse login(LoginRequest request, HttpSession session) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("없는 회원입니다.")
        );
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }
        SessionUser sessionUser = new SessionUser(
                user.getUserId(),
                user.getEmail(),
                user.getUserName()
        );
        session.setAttribute("loginUser", sessionUser);

        return new LoginResponse(
                sessionUser.getId(),
                sessionUser.getEmail(),
                sessionUser.getUserName()
        );

    }
}
