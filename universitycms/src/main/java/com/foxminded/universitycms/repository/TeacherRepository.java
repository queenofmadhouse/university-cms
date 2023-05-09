package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
