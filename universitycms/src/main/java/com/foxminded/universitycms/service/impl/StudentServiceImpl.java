package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.repository.StudentRepository;
import com.foxminded.universitycms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
}
