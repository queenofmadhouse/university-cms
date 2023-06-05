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

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Override
    @EntityGraph(attributePaths = {"courses"})
    List<Teacher> findAll();

    @Query("SELECT c FROM Course c WHERE c.teachers = :teacher")
    List<Course> findAllCoursesRelatedToTeacher(Teacher teacher);

    Optional<Teacher> findByFirstName(String firstName);

    Optional<Teacher> findByLastName(String lastName);

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findByPassword(String password);

    @Transactional
    @Modifying
    void deleteByUserId(long id);
}
