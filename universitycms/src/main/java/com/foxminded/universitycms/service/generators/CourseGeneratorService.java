package com.foxminded.universitycms.service.generators;

import com.foxminded.universitycms.datagenerator.CourseGenerator;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseGeneratorService implements DataGeneratorService {

    private final CourseGenerator courseGenerator;
    private final CourseRepository courseRepository;

    @Override
    public void generateData() {
        List<Course> generatedCourses = courseGenerator.generateData();

        courseRepository.saveAll(generatedCourses);
    }
}
