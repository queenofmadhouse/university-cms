package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.TeacherCourseRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCourseRelationRepository extends JpaRepository<TeacherCourseRelation, Long> {
}
