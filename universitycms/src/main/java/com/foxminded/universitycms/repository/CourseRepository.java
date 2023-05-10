package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    public Optional<Course> findByCourseName(String firstName);

    public Optional<Course> findByCourseDescription(String courseDescription);

    public Optional<Course> findByCourseId(long id);

    public List<Course> findAllCourse();

    @Transactional
    @Modifying
    public void deleteByCourseId(long id);
}
