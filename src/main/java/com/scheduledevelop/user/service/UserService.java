package com.scheduledevelop.user.service;

import com.scheduledevelop.common.config.PasswordEncoder;
import com.scheduledevelop.user.dto.*;
import com.scheduledevelop.user.entity.User;
import com.scheduledevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 *
 * 회원 조회, 회원 생성, 회원 정보 확인 등
 * 사용자 도메인에서 필요한 핵심 로직을 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("중복된 이메일 입니다");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(
                request.getUserName(),
                request.getEmail(),
                encodedPassword
        );
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(
                savedUser.getUserId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public GetUserResponse getOne(Long userId) {
        User user = getUserByIdOrThrow(userId);
        return new GetUserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetUserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(GetUserResponse::from)
                .toList();
    }

    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        User user = getUserByIdOrThrow(userId);
        user.updateUser(
                request.getUserName(),
                request.getEmail()
        );
        return new UpdateUserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long userId) {
        boolean existence = userRepository.existsById(userId);
        if (!existence) {
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        }
        userRepository.deleteById(userId);
    }

    public User getUserByIdOrThrow (Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("해당 유저는 존재하지 않습니다.")
        );
    }
}
