package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Student;

import java.util.List;

public interface StudentService {

    Student findById(long id);

    void saveAll(List<Student> students);
}
