package com.scheduledevelop.user.controller;


import com.scheduledevelop.auth.dto.SessionUser;
import com.scheduledevelop.user.dto.*;
import com.scheduledevelop.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOne(userId));
    }

    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getUserList() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @PatchMapping
    public ResponseEntity<UpdateUserResponse> updateUser (
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(sessionUser.getId(), request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long userId
    ) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        if (!sessionUser.getId().equals(userId)) {
            throw new IllegalStateException("본인 계정만 삭제할 수 있습니다.");
        }

        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
