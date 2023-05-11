package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    public List<Schedule> findAllByGroupAndLessonStartAndLessonEnd(
            Group group, LocalDateTime lessonStart, LocalDateTime lessonEnd);

    public List<Schedule> findAllByTeacherAndLessonStartAndLessonEnd(
            Teacher teacher, LocalDateTime lessonStart, LocalDateTime lessonEnd);



    @Transactional
    @Modifying
    public void deleteByScheduleId(long id);
}
