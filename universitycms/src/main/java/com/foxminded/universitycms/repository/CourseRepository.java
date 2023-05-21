package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseName(String firstName);

    Optional<Course> findByCourseDescription(String courseDescription);

    Optional<Course> findByCourseId(long id);

    @Query(value = "SELECT course_id " +
            "FROM university.teachers_courses_relation " +
            "WHERE user_id = :teacher", nativeQuery = true)
    List<Course> findAllByTeacher(Teacher teacher);

    @Transactional
    @Modifying
    void deleteByCourseId(long id);
}
