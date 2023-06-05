package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Override
    @EntityGraph(attributePaths = {"teachers"})
    List<Course> findAll();

    Optional<Course> findByCourseName(String firstName);

    Optional<Course> findByCourseDescription(String courseDescription);

    Optional<Course> findByCourseId(long id);

    @Query (value = "SELECT c FROM Course c JOIN c.teachers t WHERE t = :teacher")
    List<Course> findAllByTeacher(Teacher teacher);

    @Transactional
    @Modifying
    void deleteByCourseId(long id);
}
