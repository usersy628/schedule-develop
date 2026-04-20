package com.scheduledevelop.user.controller;

import com.scheduledevelop.user.dto.CreateUserRequest;
import com.scheduledevelop.user.dto.CreateUserResponse;
import com.scheduledevelop.user.dto.GetUserResponse;
import com.scheduledevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
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

}
