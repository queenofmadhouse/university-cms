package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.exception.DatabaseRuntimeException;
import com.foxminded.universitycms.repository.ScheduleRepository;
import com.foxminded.universitycms.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void saveAll(List<Schedule> schedules) {
        scheduleRepository.saveAll(schedules);
    }

    @Override
    public Map<LocalDate, List<Schedule>> findScheduleByTeacher(Teacher teacher, int days) {
        LocalDate today = LocalDate.now();
        LocalDateTime startDate = today.atStartOfDay();
        LocalDateTime endDate = today.plusDays(days).atTime(23, 59, 59);

        List<Schedule> schedules = scheduleRepository.findAllByTeacherAndLessonStartBetween(teacher, startDate, endDate);

        return schedules.stream()
                .collect(Collectors.groupingBy(schedule -> schedule.getLessonStart().toLocalDate()));
    }

    @Override
    public Map<LocalDate, List<Schedule>> findScheduleByGroup(Group group, int days) {
        LocalDate today = LocalDate.now();
        LocalDateTime startDate = today.atStartOfDay();
        LocalDateTime endDate = today.plusDays(days).atTime(23, 59, 59);

        List<Schedule> schedules = scheduleRepository.findAllByGroupAndLessonStartBetween(group, startDate, endDate);

        return schedules.stream()
                .collect(Collectors.groupingBy(schedule -> schedule.getLessonStart().toLocalDate()));
    }

    @Override
    public Schedule findById(long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new DatabaseRuntimeException("Can't find schedule by id: " + id));
    }

    @Override
    public List<LocalTime> findFreeTimeForTeacherAndGroup(LocalDate date, Teacher teacher, Group group) {

        LocalDateTime startTime = date.atStartOfDay().plusHours(8);
        LocalDateTime endTime = date.atStartOfDay().plusHours(18);

        List<Schedule> scheduleForTeacher = scheduleRepository.findAllByTeacherAndLessonStartBetween(teacher, startTime, endTime);
        List<Schedule> scheduleForGroup = scheduleRepository.findAllByGroupAndLessonStartBetween(group, startTime, endTime);

        List<Schedule> combinedSchedule = new ArrayList<>();
        combinedSchedule.addAll(scheduleForTeacher);
        combinedSchedule.addAll(scheduleForGroup);
        combinedSchedule.sort(Comparator.comparing(Schedule::getLessonStart));

        List<LocalTime> freeTimeSlots = new ArrayList<>();
        LocalDateTime nextFreeTimeSlotStart = startTime;

        for (Schedule schedule : combinedSchedule) {
            while (schedule.getLessonStart().isAfter(nextFreeTimeSlotStart.plusMinutes(60))) {
                freeTimeSlots.add(nextFreeTimeSlotStart.toLocalTime());
                nextFreeTimeSlotStart = nextFreeTimeSlotStart.plusMinutes(60);
            }

            nextFreeTimeSlotStart = schedule.getLessonEnd().plusMinutes(10);  // +10 minutes for break
        }

        while (endTime.minusMinutes(60).isAfter(nextFreeTimeSlotStart)) {
            freeTimeSlots.add(nextFreeTimeSlotStart.toLocalTime());
            nextFreeTimeSlotStart = nextFreeTimeSlotStart.plusMinutes(60);
        }

        return freeTimeSlots;
    }
}
