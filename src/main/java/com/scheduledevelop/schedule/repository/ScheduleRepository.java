package com.scheduledevelop.schedule.repository;

import com.scheduledevelop.schedule.dto.ScheduleWithCommentCount;
import com.scheduledevelop.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("""
        select new com.scheduledevelop.schedule.dto.ScheduleWithCommentCount(
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
        """)
    Page<ScheduleWithCommentCount> findSchedulePage(Pageable pageable);
}