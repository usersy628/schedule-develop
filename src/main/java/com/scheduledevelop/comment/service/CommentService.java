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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ScheduleService scheduleService;

    @Transactional
    public CreateCommentResponse save(CreateCommentRequest request) {
        User user = userService.getUserById(request.getUserId());
        Schedule schedule = scheduleService.getScheduleById(request.getScheduleId());
        Comment comment = new Comment(
                request.getContent(),
                user,
                schedule
        );
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getUser().getUserId(),
                savedComment.getSchedule().getId(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> getAll(Long scheduleId) {
        List<Comment> comments = commentRepository.findByScheduleIdOrderByModifiedAtDesc(scheduleId);
        List<GetCommentResponse> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            GetCommentResponse dto = new GetCommentResponse(
                    comment.getId(),
                    comment.getContent(),
                    comment.getUser().getUserId(),
                    comment.getSchedule().getId(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }
}
