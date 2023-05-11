package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.repository.ScheduleRepository;
import com.foxminded.universitycms.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Map<LocalDateTime, List<Schedule>> findScheduleForMonthForTeacher(Teacher teacher) {
        Map<LocalDateTime, List<Schedule>> scheduleForMonth = new HashMap<>();

        LocalDate today = LocalDate.now();

        for (int i = 0; i < 30; i++) {

            LocalDateTime dateStartForStep = today.atStartOfDay().plusDays(i);
            LocalDateTime dateEndForStep = dateStartForStep
                    .plusHours(23)
                    .plusMinutes(59)
                    .plusSeconds(59);

            List<Schedule> scheduleForDay = scheduleRepository.findAllByTeacherAndLessonStartBetween(
                    teacher, dateStartForStep, dateEndForStep);
            scheduleForMonth.put(dateStartForStep, scheduleForDay);
        }

        return scheduleForMonth;
    }

    public Map<LocalDateTime, List<Schedule>> findScheduleForMonthForGroup(Group group) {
        Map<LocalDateTime, List<Schedule>> scheduleForMonth = new HashMap<>();

        LocalDate today = LocalDate.now();

        for (int i = 0; i < 30; i++) {

            LocalDateTime dateStartForStep = today.atStartOfDay().plusDays(i);
            LocalDateTime dateEndForStep = dateStartForStep
                    .plusHours(23)
                    .plusMinutes(59)
                    .plusSeconds(59);

            List<Schedule> scheduleForDay = scheduleRepository.findAllByGroupAndLessonStartBetween(
                    group, dateStartForStep, dateEndForStep);
            scheduleForMonth.put(dateStartForStep, scheduleForDay);
        }

        return scheduleForMonth;
    }
}
