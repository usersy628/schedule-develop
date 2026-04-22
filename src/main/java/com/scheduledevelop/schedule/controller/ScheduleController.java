package com.scheduledevelop.schedule.controller;

import com.scheduledevelop.auth.dto.SessionUser;
import com.scheduledevelop.schedule.dto.*;
import com.scheduledevelop.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @Valid @RequestBody CreateScheduleRequest request
    ) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(sessionUser.getId(), request));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<GetSchedulePageResponse>> getSchedulePage(
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findSchedulePage(pageable));
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId,
            @Valid @RequestBody UpdateScheduleRequest request
    ) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, sessionUser.getId(), request));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId) {
        scheduleService.delete(scheduleId, sessionUser.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}