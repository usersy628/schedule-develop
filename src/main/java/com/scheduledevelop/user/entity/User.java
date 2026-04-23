package com.scheduledevelop.user.entity;

import com.scheduledevelop.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 정보를 저장하는 엔티티 클래스입니다.
 *
 * 사용자의 이름, 이메일, 비밀번호 등의 회원 정보를 관리하며,
 * 일정(Schedule) 및 댓글(Comment)과 연관관계를 가질 수 있습니다.
 *
 * 데이터베이스의 user 테이블과 매핑됩니다.
 */
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void updateUser(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
