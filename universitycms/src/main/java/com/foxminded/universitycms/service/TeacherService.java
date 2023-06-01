package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Teacher findByUserId(long id);

    void saveAll(List<Teacher> teachers);
}
