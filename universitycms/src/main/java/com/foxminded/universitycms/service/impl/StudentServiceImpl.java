package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.repository.StudentRepository;
import com.foxminded.universitycms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
}