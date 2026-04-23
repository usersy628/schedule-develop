package com.scheduledevelop.comment.repository;

import com.scheduledevelop.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 댓글(Comment) 엔티티의 데이터베이스 접근을 담당하는 Repository입니다.
 *
 * 댓글 저장, 조회, 수정, 삭제를 위한 데이터 접근 기능을 제공합니다.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByScheduleIdOrderByModifiedAtDesc(Long scheduleId);
    void deleteByScheduleId(Long scheduleId);
}
