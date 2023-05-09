package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
