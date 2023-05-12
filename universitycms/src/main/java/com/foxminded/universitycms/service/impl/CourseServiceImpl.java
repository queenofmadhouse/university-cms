package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.repository.CourseRepository;
import com.foxminded.universitycms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
}
