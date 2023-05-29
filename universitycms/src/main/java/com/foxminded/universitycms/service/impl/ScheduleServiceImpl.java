package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.repository.ScheduleRepository;
import com.foxminded.universitycms.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Map<LocalDate, List<Schedule>> getScheduleByTeacher(Teacher teacher, int days) {
        LocalDate today = LocalDate.now();
        LocalDateTime startDate = today.atStartOfDay();
        LocalDateTime endDate = today.plusDays(days).atTime(23, 59, 59);

        List<Schedule> schedules = scheduleRepository.findAllByTeacherAndLessonStartBetween(teacher, startDate, endDate);

        return schedules.stream()
                .collect(Collectors.groupingBy(schedule -> schedule.getLessonStart().toLocalDate()));
    }

    @Override
    public Map<LocalDate, List<Schedule>> getScheduleByGroup(Group group, int days) {
        LocalDate today = LocalDate.now();
        LocalDateTime startDate = today.atStartOfDay();
        LocalDateTime endDate = today.plusDays(days).atTime(23, 59, 59);

        List<Schedule> schedules = scheduleRepository.findAllByGroupAndLessonStartBetween(group, startDate, endDate);

        return schedules.stream()
                .collect(Collectors.groupingBy(schedule -> schedule.getLessonStart().toLocalDate()));
    }

    @Override
    public Schedule getScheduleById(long id) {
        return scheduleRepository.getScheduleByScheduleId(id).orElseThrow(RuntimeException::new);
    }
}
