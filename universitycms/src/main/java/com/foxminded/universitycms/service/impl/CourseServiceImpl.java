package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.repository.CourseRepository;
import com.foxminded.universitycms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<Course> findAllByTeacher(Teacher teacher) {
        return courseRepository.findAllByTeacher(teacher);
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void saveAll(List<Course> courses) {
        courseRepository.saveAll(courses);
    }
}
