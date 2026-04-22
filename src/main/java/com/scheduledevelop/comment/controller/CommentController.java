package com.scheduledevelop.comment.controller;

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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponse> saveComment(@Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(request));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<List<GetCommentResponse>> getComments(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAll(scheduleId));
    }
}
