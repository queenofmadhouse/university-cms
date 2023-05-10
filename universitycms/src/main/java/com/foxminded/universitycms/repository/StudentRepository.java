package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public Optional<Student> findByUserId(long id);

    public Optional<Student> findByFirstName(String firstName);

    public Optional<Student> findByLastName(String lastName);

    public Optional<Student> findByEmail(String email);

    public Optional<Student> findByPassword(String password);

    public List<Student> findAllStudent();

    public List<Student> findAllByGroup(Group group);

    @Transactional
    @Modifying
    public void deleteByUserId(long id);
}
