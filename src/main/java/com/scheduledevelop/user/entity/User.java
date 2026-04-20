package com.scheduledevelop.user.entity;

import com.scheduledevelop.schedule.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }
}
