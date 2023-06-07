package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.exception.DatabaseRuntimeException;
import com.foxminded.universitycms.repository.TeacherRepository;
import com.foxminded.universitycms.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public List<Course> findAllCoursesRelatedToTeacher(long teacherId) {

        Teacher teacher = findById(teacherId);

        return teacherRepository.findAllCoursesRelatedToTeacher(teacher);
    }

    @Override
    public Teacher findById(long id) {
        return teacherRepository.findById(id).orElseThrow(() ->
                new DatabaseRuntimeException("Can't find teacher by id: " + id));
    }

    @Override
    public void saveAll(List<Teacher> teachers) {
        teacherRepository.saveAll(teachers);
    }
}
