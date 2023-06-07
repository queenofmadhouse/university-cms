package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Classroom;
import com.foxminded.universitycms.repository.ClassroomRepository;
import com.foxminded.universitycms.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Override
    public List<Classroom> findFreeClassrooms(LocalDateTime timeSlotStart) {
        LocalDateTime timeSlotEnd = timeSlotStart.plusMinutes(50);
        return classroomRepository.findFreeClassrooms(timeSlotStart, timeSlotEnd);
    }

    @Override
    public void saveAll(List<Classroom> classrooms) {
        classroomRepository.saveAll(classrooms);
    }

    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }
}
