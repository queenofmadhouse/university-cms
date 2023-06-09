package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Override
    @EntityGraph(attributePaths = {"courses"})
    List<Group> findAll();

    Optional<Group> findByGroupName(String firstName);

    Optional<Group> findByGroupId(long id);

    @Query (value = "SELECT g FROM Group g JOIN g.courses c WHERE c = :course")
    List<Group> findAllByCourse(Course course);

    @Transactional
    @Modifying
    void deleteByGroupId(long id);
}
