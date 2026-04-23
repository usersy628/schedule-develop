package com.scheduledevelop.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 댓글 생성 또는 수정 요청 데이터를 전달받기 위한 DTO입니다.
 *
 * 댓글 내용과 작성자 정보 등 댓글 처리에 필요한 요청 값을 담습니다.
 */
@Getter
public class CreateCommentRequest {

    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String content;

    @NotNull(message = "일정 ID는 필수입니다.")
    private Long scheduleId;
}
