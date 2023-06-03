package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.exception.DatabaseRuntimeException;
import com.foxminded.universitycms.repository.StudentRepository;
import com.foxminded.universitycms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student findById(long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new DatabaseRuntimeException("Can't find student by id: " + id));
    }

    @Override
    public void saveAll(List<Student> students) {
        studentRepository.saveAll(students);
    }

    @Override
    public boolean isTableEmpty() {
        return studentRepository.count() == 0;
    }
}
