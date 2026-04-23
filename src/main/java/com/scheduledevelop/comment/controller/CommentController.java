package com.scheduledevelop.comment.controller;

import com.scheduledevelop.auth.dto.SessionUser;
import com.scheduledevelop.comment.dto.CreateCommentRequest;
import com.scheduledevelop.comment.dto.CreateCommentResponse;
import com.scheduledevelop.comment.dto.GetCommentResponse;
import com.scheduledevelop.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 댓글 관련 HTTP 요청을 처리하는 컨트롤러 클래스입니다.
 *
 * 댓글 등록, 조회, 수정, 삭제 요청을 받아
 * 서비스 계층으로 전달하고 처리 결과를 응답합니다.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponse> saveComment(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @Valid @RequestBody CreateCommentRequest request
    ) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(sessionUser.getId(), request));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<List<GetCommentResponse>> getComments(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAll(scheduleId));
    }
}
