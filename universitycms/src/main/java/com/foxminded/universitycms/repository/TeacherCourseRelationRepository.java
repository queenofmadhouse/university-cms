package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.TeacherCourseRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherCourseRelationRepository extends JpaRepository<TeacherCourseRelation, Long> {

    public List<TeacherCourseRelation> findTeacherCourseRelationByCourse(Course course);

    public List<TeacherCourseRelation> findTeacherCourseRelationByTeacher(Teacher teacher);

    @Transactional
    @Modifying
    public void deleteById(long id);
}
