package com.scheduledevelop.comment.dto;

import com.scheduledevelop.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 댓글 정보를 클라이언트에 응답하기 위한 DTO입니다.
 *
 * 댓글 식별자, 내용, 작성자 정보, 작성일, 수정일 등을 포함하여
 * 댓글 조회 결과를 반환할 때 사용됩니다.
 */
@Getter
public class GetCommentResponse {

    private final Long id;
    private final String content;
    private final Long userId;
    private final Long scheduleId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCommentResponse(Long id, String content, Long userId, Long scheduleId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static GetCommentResponse from(Comment comment) {
        return new GetCommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUserId(),
                comment.getSchedule().getId(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
