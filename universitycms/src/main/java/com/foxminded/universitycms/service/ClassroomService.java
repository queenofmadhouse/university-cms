package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Classroom;

import java.time.LocalDateTime;
import java.util.List;

public interface ClassroomService {

    List<Classroom> findFreeClassrooms(LocalDateTime timeSlotStart);

    void saveAll(List<Classroom> classrooms);

    List<Classroom> findAll();
}
