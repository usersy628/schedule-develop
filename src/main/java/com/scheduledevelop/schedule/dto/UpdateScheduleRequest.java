package com.scheduledevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 일정 수정 요청 데이터를 전달받기 위한 DTO입니다.
 *
 * 수정할 일정의 제목, 내용 등의 변경 정보를 담습니다.
 */
@Getter
public class UpdateScheduleRequest {

    @NotBlank(message = "일정 제목은 필수입니다.")
    @Size(max = 10, message = "일정 제목은 10글자 이내여야 합니다.")
    private String title;

    @NotBlank(message = "할일 내용은 필수입니다.")
    private String content;

}
