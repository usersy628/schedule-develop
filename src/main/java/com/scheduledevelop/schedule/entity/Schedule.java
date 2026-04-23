package com.scheduledevelop.schedule.entity;

import com.scheduledevelop.common.entity.BaseEntity;
import com.scheduledevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 일정 정보를 저장하는 엔티티 클래스입니다.
 *
 * 일정의 제목, 내용, 작성일, 수정일을 관리하며,
 * 작성자(User)와 연관관계를 가집니다.
 *
 * 데이터베이스의 schedule 테이블과 매핑됩니다.
 */
@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateSchedule(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
