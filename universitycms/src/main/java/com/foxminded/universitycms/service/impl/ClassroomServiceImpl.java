package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Classroom;
import com.foxminded.universitycms.repository.ClassroomRepository;
import com.foxminded.universitycms.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Override
    public void saveAll(List<Classroom> classrooms) {
        classroomRepository.saveAll(classrooms);
    }

    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }
}
