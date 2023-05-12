package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public Optional<Teacher> findByUserId(long id);

    public Optional<Teacher> findByFirstName(String firstName);

    public Optional<Teacher> findByLastName(String lastName);

    public Optional<Teacher> findByEmail(String email);

    public Optional<Teacher> findByPassword(String password);

    @Modifying
    @Query(value = "INSERT INTO university.teachers_courses_relation (user_id, course_id) " +
            "VALUES (:teacher, :course)", nativeQuery = true)
    public void addTeacherToCourse(Teacher teacher, Course course);

    @Transactional
    @Modifying
    public void deleteByUserId(long id);
}
