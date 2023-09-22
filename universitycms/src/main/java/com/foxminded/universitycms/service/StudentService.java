package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Student;

import java.util.List;

public interface StudentService {

    Student findByEmail(String email);

    void saveAll(List<Student> students);
}
