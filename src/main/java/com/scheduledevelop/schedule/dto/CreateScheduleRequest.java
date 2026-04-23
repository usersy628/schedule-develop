package com.scheduledevelop.schedule.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 일정 생성 요청 데이터를 전달받기 위한 DTO입니다.
 *
 * 일정 작성 시 필요한 제목, 내용, 작성자 정보 등을 담습니다.
 */
@Getter
public class CreateScheduleRequest {

    @NotBlank(message = "일정 제목은 필수입니다.")
    @Size(max = 10, message = "일정 제목은 10글자 이내여야 합니다.")
    private String title;

    @NotBlank(message = "할일 내용은 필수입니다.")
    private String content;
}
