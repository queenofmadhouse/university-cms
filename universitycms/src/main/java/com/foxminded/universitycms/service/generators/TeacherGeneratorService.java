package com.foxminded.universitycms.service.generators;

import com.foxminded.universitycms.datagenerator.TeacherGenerator;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.repository.CourseRepository;
import com.foxminded.universitycms.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherGeneratorService implements DataGeneratorService {

    private final TeacherGenerator teacherGenerator;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void generateData() {
        List<Course> allCourses = courseRepository.findAll();
        List<Teacher> generatedTeachers = teacherGenerator.generateData(allCourses);

        teacherRepository.saveAll(generatedTeachers);
    }
}
