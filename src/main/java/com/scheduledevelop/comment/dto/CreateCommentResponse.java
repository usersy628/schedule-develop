package com.scheduledevelop.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {

    private final Long id;
    private final String content;
    private final Long userId;
    private final Long scheduleId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CreateCommentResponse(Long id, String content, Long userId, Long scheduleId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
