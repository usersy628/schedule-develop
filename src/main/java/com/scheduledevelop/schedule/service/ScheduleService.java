package com.scheduledevelop.schedule.service;

import com.scheduledevelop.schedule.dto.*;
import com.scheduledevelop.schedule.entity.Schedule;
import com.scheduledevelop.schedule.repository.ScheduleRepository;
import com.scheduledevelop.user.entity.User;
import com.scheduledevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        User user = userService.getUserById(request.getUserId());
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                user
                );
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                saveSchedule.getId(),
                user.getUserId(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정이 없습니다")
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getUser().getUserId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public Page<GetSchedulePageResponse> findSchedulePage(Pageable pageable) {
        return scheduleRepository.findSchedulePage(pageable);
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정이 없습니다.")
        );
        schedule.updateSchedule(
                request.getTitle(),
                request.getContent(),
                schedule.getUser()
                );
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getUser().getUserId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long scheduleId) {
        boolean existence = scheduleRepository.existsById(scheduleId);
        if (!existence) {
            throw new IllegalStateException("해당 일정이 존재하지 않습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }

    public Schedule getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정이 존재하지 않습니다.")
        );
    }
}
