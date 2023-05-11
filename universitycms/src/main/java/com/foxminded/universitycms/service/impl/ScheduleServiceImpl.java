package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.repository.ScheduleRepository;
import com.foxminded.universitycms.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
}
