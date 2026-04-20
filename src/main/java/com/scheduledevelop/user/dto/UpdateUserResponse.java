package com.scheduledevelop.user.dto;

import lombok.Getter;

@Getter
public class UpdateUserResponse {

    private final Long id;
    private final String userName;
    private final String email;

    public UpdateUserResponse(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
}
