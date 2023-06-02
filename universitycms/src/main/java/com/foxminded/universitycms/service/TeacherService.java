package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher findByUserId(long id);

    void saveAll(List<Teacher> teachers);
}
