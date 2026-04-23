package com.scheduledevelop.comment.service;

import com.scheduledevelop.comment.dto.CreateCommentRequest;
import com.scheduledevelop.comment.dto.CreateCommentResponse;
import com.scheduledevelop.comment.dto.GetCommentResponse;
import com.scheduledevelop.comment.entity.Comment;
import com.scheduledevelop.comment.repository.CommentRepository;
import com.scheduledevelop.schedule.entity.Schedule;
import com.scheduledevelop.schedule.service.ScheduleService;
import com.scheduledevelop.user.entity.User;
import com.scheduledevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 댓글 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 *
 * 댓글 생성, 조회, 수정, 삭제 기능을 수행하며,
 * 작성자 확인 및 일정과의 연관관계 검증을 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ScheduleService scheduleService;

    @Transactional
    public CreateCommentResponse save(Long userId, CreateCommentRequest request) {
        User user = userService.getUserByIdOrThrow(userId);
        Schedule schedule = scheduleService.getScheduleByIdOrThrow(request.getScheduleId());
        Comment comment = new Comment(
                request.getContent(),
                user,
                schedule
        );
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContent(),
                user.getUserId(),
                schedule.getId(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> getAll(Long scheduleId) {
        return commentRepository.findByScheduleIdOrderByModifiedAtDesc(scheduleId).stream()
                .map(GetCommentResponse::from)
                .toList();
    }
}
