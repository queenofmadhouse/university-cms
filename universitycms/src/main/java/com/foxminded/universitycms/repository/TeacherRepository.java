package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByFirstName(String firstName);

    Optional<Teacher> findByLastName(String lastName);

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findByPassword(String password);

    @Transactional
    @Modifying
    void deleteByUserId(long id);
}
