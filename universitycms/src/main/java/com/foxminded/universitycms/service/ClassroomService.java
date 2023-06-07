package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Classroom;

import java.util.List;

public interface ClassroomService {

    void saveAll(List<Classroom> classrooms);

    List<Classroom> findAll();

    boolean isTableEmpty();
}
