package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUserId(long id);

    Optional<Student> findByFirstName(String firstName);

    Optional<Student> findByLastName(String lastName);

    Optional<Student> findByEmail(String email);

    Optional<Student> findByPassword(String password);

    List<Student> findAllByGroup(Group group);

    @Transactional
    @Modifying
    void deleteByUserId(long id);
}
