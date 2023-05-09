package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
