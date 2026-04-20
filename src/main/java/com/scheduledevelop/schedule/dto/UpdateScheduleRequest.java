package com.scheduledevelop.schedule.dto;

import com.scheduledevelop.user.entity.User;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private User user;
    private String title;
    private String content;

}
