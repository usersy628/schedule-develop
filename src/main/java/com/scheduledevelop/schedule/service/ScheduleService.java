package com.scheduledevelop.schedule.service;

import com.scheduledevelop.comment.repository.CommentRepository;
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

/**
 * 일정 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 *
 * 일정 생성, 조회, 수정, 삭제 기능을 수행하며,
 * 작성자 검증 및 권한 확인과 같은 핵심 로직을 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateScheduleResponse save(Long userId, CreateScheduleRequest request) {
        User user = userService.getUserByIdOrThrow(userId);
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
        Schedule schedule = getScheduleByIdOrThrow(scheduleId);
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
    public UpdateScheduleResponse update(Long scheduleId, Long loginUserId, UpdateScheduleRequest request) {
        Schedule schedule = getScheduleByIdOrThrow(scheduleId);
        validateScheduleOwner(schedule, loginUserId);
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
    public void delete(Long scheduleId, Long loginUserId) {
        Schedule schedule = getScheduleByIdOrThrow(scheduleId);
        boolean existence = scheduleRepository.existsById(scheduleId);
        if (!existence) {
            throw new IllegalStateException("해당 일정이 존재하지 않습니다.");
        }
        validateScheduleOwner(schedule, loginUserId);
        commentRepository.deleteByScheduleId(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }

    public Schedule getScheduleByIdOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정이 존재하지 않습니다.")
        );
    }

    private void validateScheduleOwner(Schedule schedule, Long loginUserId) {
        if (!schedule.getUser().getUserId().equals(loginUserId)) {
            throw new IllegalStateException("본인 일정만 수정 또는 삭제할 수 있습니다.");
        }
    }
}
