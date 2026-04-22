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
