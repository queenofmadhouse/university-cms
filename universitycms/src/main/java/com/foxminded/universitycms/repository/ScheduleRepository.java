package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByGroupAndLessonStartBetween(
            Group group, LocalDateTime lessonStart, LocalDateTime lessonEnd);

    List<Schedule> findAllByTeacherAndLessonStartBetween(
            Teacher teacher, LocalDateTime lessonStart, LocalDateTime lessonEnd);

    Optional<Schedule> getScheduleByScheduleId(long scheduleId);


    @Transactional
    @Modifying
    void deleteByScheduleId(long id);
}
