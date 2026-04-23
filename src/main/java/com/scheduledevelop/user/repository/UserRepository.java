package com.scheduledevelop.user.repository;

import com.scheduledevelop.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 사용자(User) 엔티티의 데이터베이스 접근을 담당하는 Repository입니다.
 *
 * JpaRepository를 상속받아 사용자 정보를 저장, 조회, 수정, 삭제하는
 * 기본적인 데이터 접근 기능을 제공합니다.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
