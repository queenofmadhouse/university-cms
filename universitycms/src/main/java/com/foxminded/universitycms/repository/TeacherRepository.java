package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public Optional<Teacher> findByUserId(long id);

    public Optional<Teacher> findByFirstName(String firstName);

    public Optional<Teacher> findByLastName(String lastName);

    public Optional<Teacher> findByEmail(String email);

    public Optional<Teacher> findByPassword(String password);

    @Transactional
    @Modifying
    public void deleteByUserId(long id);
}
