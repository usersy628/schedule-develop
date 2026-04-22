package com.scheduledevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    @NotBlank(message = "일정 제목은 필수입니다.")
    @Size(max = 10, message = "일정 제목은 10글자 이내여야 합니다.")
    private String title;

    @NotBlank(message = "할일 내용은 필수입니다.")
    private String content;

}
