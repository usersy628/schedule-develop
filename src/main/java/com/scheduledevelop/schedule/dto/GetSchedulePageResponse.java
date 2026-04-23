package com.scheduledevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 일정 목록 조회 결과를 응답하기 위한 DTO입니다.
 *
 * 여러 개의 일정 데이터와 함께 페이지 정보 또는 조회 결과 목록을
 * 클라이언트에 전달할 때 사용됩니다.
 */
@Getter
public class GetSchedulePageResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final Long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long userId;

    public GetSchedulePageResponse(Long id, String title, String content, Long commentCount, LocalDateTime createdAt, LocalDateTime modifiedAt, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.userId = userId;
    }
}
