package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.repository.TeacherRepository;
import com.foxminded.universitycms.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Teacher findByUserId(long id) {
        return teacherRepository.findByUserId(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void saveAll(List<Teacher> teachers) {
        teacherRepository.saveAll(teachers);
    }
}
