package com.scheduledevelop.auth.service;

import com.scheduledevelop.auth.dto.LoginRequest;
import com.scheduledevelop.auth.dto.LoginResponse;
import com.scheduledevelop.auth.dto.SessionUser;
import com.scheduledevelop.user.entity.User;
import com.scheduledevelop.user.repository.UserRepository;
import com.scheduledevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public LoginResponse login(LoginRequest request, HttpSession session) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("없는 회원입니다.")
        );
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
