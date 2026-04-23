package com.scheduledevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 일정 정보를 클라이언트에 응답하기 위한 DTO입니다.
 *
 * 일정의 식별자, 제목, 내용, 작성자 정보, 작성일, 수정일 등을 포함하여
 * 조회 결과를 반환할 때 사용됩니다.
 */
@Getter
public class CreateScheduleResponse {

    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponse(Long id, Long userId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
