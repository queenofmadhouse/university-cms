package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    public Optional<Group> findByGroupName(String firstName);

    public Optional<Group> findByGroupId(long id);

    @Query (value = "SELECT group_id " +
            "FROM university.groups_courses_relation " +
            "WHERE course_id = :course", nativeQuery = true)
    public List<Group> findAllByCourses(Course course);

    @Modifying
    @Query(value = "INSERT INTO university.groups_courses_relation (group_id, course_id) " +
            "VALUES (:group, :course)", nativeQuery = true)
    public void addGroupToCourse(Group group, Course course);

    @Transactional
    @Modifying
    public void deleteByGroupId(long id);
}
