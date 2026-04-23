package com.scheduledevelop.auth.controller;

import com.scheduledevelop.auth.dto.LoginRequest;
import com.scheduledevelop.auth.dto.LoginResponse;
import com.scheduledevelop.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 및 인가 관련 HTTP 요청을 처리하는 컨트롤러 클래스입니다.
 *
 * 로그인, 로그아웃, 인증 확인 등
 * 사용자 인증 절차와 관련된 요청을 처리합니다.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        LoginResponse response = authService.login(request, session);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
