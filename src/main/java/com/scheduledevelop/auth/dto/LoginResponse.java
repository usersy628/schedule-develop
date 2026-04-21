package com.scheduledevelop.auth.dto;

import lombok.Getter;

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
