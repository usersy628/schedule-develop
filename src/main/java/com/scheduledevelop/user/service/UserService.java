package com.scheduledevelop.user.service;

import com.scheduledevelop.user.dto.CreateUserRequest;
import com.scheduledevelop.user.dto.CreateUserResponse;
import com.scheduledevelop.user.entity.User;
import com.scheduledevelop.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


}
