package com.scheduledevelop.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String userName;
    private String title;
    private String content;
}
