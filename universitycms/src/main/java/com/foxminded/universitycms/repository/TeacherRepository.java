package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByUserId(long id);

    Optional<Teacher> findByFirstName(String firstName);

    Optional<Teacher> findByLastName(String lastName);

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findByPassword(String password);

    @Modifying
    @Query(value = "INSERT INTO university.teachers_courses_relation (user_id, course_id) " +
            "VALUES (:teacher, :course)", nativeQuery = true)
    void addTeacherToCourse(Teacher teacher, Course course);

    @Transactional
    @Modifying
    void deleteByUserId(long id);
}
