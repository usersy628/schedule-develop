package com.scheduledevelop.schedule.repository;

import com.scheduledevelop.schedule.dto.GetSchedulePageResponse;
import com.scheduledevelop.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 일정(Schedule) 엔티티의 데이터베이스 접근을 담당하는 Repository입니다.
 *
 * 일정 저장, 전체 조회, 단건 조회, 수정, 삭제를 위한
 * 데이터 접근 기능을 제공합니다.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(
        value = """
            select new com.scheduledevelop.schedule.dto.GetSchedulePageResponse(
                s.id,
                s.title,
                s.content,
                count(c.id),
                s.createdAt,
                s.modifiedAt,
                s.user.userId
            )
            from Schedule s
            left join Comment c on c.schedule.id = s.id
            group by s.id, s.title, s.content, s.createdAt, s.modifiedAt, s.user.userId
            order by s.modifiedAt desc
        """,
        countQuery = """
            select count(s)
            from Schedule s
        """
    )
    Page<GetSchedulePageResponse> findSchedulePage(Pageable pageable);
    // JPQL에서는 limit/offset으로 접근하지 않고 Pageable을 사용
}