package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;

import java.util.List;

public interface CourseService {

    List<Course> findAllByTeacher(Teacher teacher);

    List<Course> findAll();

    Course findById(Long id);

    void saveAll(List<Course> courses);

    boolean isTableEmpty();
}
