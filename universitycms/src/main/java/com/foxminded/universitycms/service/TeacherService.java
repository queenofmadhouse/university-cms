package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Course> findAllCoursesRelatedToTeacher(long teacherId);

    Teacher findById(long id);

    void saveAll(List<Teacher> teachers);
}
