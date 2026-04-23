package com.scheduledevelop.comment.entity;

import com.scheduledevelop.common.entity.BaseEntity;
import com.scheduledevelop.schedule.entity.Schedule;
import com.scheduledevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 댓글 정보를 저장하는 엔티티 클래스입니다.
 *
 * 댓글 내용, 작성일, 수정일을 관리하며,
 * 사용자(User)와 일정(Schedule)과의 연관관계를 가집니다.
 *
 * 데이터베이스의 comment 테이블과 매핑됩니다.
 */
@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment(String content, User user, Schedule schedule) {
        this.content = content;
        this.user = user;
        this.schedule = schedule;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
