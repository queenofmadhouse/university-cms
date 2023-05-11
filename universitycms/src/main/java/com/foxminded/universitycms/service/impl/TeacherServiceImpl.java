package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.repository.TeacherRepository;
import com.foxminded.universitycms.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
}
