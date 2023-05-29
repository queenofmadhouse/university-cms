package com.foxminded.universitycms.datagenerator;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;

import java.util.List;

public interface TeacherGenerator {

    List<Teacher> generateData(List<Course> courses);
}
