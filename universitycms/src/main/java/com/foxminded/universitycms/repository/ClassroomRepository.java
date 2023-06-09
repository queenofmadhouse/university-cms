package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    @Query("SELECT c FROM Classroom c WHERE c.classroomId NOT IN " +
            "(SELECT s.classroomId FROM Schedule s WHERE s.lessonStart <= :endTime AND s.lessonEnd >= :startTime)")
    List<Classroom> findFreeClassrooms(LocalDateTime startTime, LocalDateTime endTime);
}
