package com.scheduledevelop.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String userName;
    private String title;
    private String content;
}
