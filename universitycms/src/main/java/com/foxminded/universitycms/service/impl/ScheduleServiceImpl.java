package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.converter.ScheduleConverter;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.dto.ScheduleDTO;
import com.foxminded.universitycms.exception.DatabaseRuntimeException;
import com.foxminded.universitycms.repository.ScheduleRepository;
import com.foxminded.universitycms.service.ScheduleService;
import com.foxminded.universitycms.service.StudentService;
import com.foxminded.universitycms.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ScheduleConverter scheduleConverter;

    @Value("${app.constants.schedule.work-day-start-hour}")
    private int workDayStartHours;

    @Value("${app.constants.schedule.work-day-end-hour}")
    private int workDayEndHours;

    @Value("${app.constants.schedule.lesson.break-between-lessons}")
    private int breakBetweenLessons;

    @Value("${app.constants.schedule.lesson.lesson-duration}")
    private int lessonDuration;

    private static final LocalTime END_OF_DAY = LocalTime.of(23, 59, 59);


    @Override
    public void save(ScheduleDTO scheduleDTO) {

        Schedule schedule = scheduleConverter.convertFromDTOtoEntity(scheduleDTO);

        scheduleRepository.save(schedule);
    }

    @Override
    public Map<LocalDate, List<Schedule>> findScheduleByTeacher(String teacherEmail, int days) {

        Teacher teacher = teacherService.findByEmail(teacherEmail);
        LocalDate today = LocalDate.now();
        LocalDateTime startDate = today.atStartOfDay();
        LocalDateTime endDate = today.plusDays(days).atTime(END_OF_DAY);

        List<Schedule> schedules = scheduleRepository.findAllByTeacherAndLessonStartBetween(teacher, startDate, endDate);

        return schedules.stream()
                .collect(Collectors.groupingBy(schedule -> schedule.getLessonStart().toLocalDate()));
    }

    @Override
    public Map<LocalDate, List<Schedule>> findScheduleByStudent(String studentEmail, int days) {

        Group group = studentService.findByEmail(studentEmail).getGroup();
        LocalDate today = LocalDate.now();
        LocalDateTime startDate = today.atStartOfDay();
        LocalDateTime endDate = today.plusDays(days).atTime(END_OF_DAY);

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
    public void deleteById(long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<LocalTime> findFreeTimeForTeacherAndGroup(LocalDate date, Teacher teacher, Group group) {

        LocalDateTime startTime = date.atStartOfDay().plusHours(workDayStartHours);
        LocalDateTime endTime = date.atStartOfDay().plusHours(workDayEndHours);

        List<Schedule> scheduleForTeacher = scheduleRepository.findAllByTeacherAndLessonStartBetween(teacher, startTime, endTime);
        List<Schedule> scheduleForGroup = scheduleRepository.findAllByGroupAndLessonStartBetween(group, startTime, endTime);

        List<Schedule> combinedSchedule = new ArrayList<>();
        combinedSchedule.addAll(scheduleForTeacher);
        combinedSchedule.addAll(scheduleForGroup);
        combinedSchedule.sort(Comparator.comparing(Schedule::getLessonStart));

        List<LocalTime> freeTimeSlots = new ArrayList<>();
        LocalDateTime nextFreeTimeSlotStart = startTime;

        for (Schedule schedule : combinedSchedule) {
            while (schedule.getLessonStart().isAfter(nextFreeTimeSlotStart
                    .plusMinutes(lessonDuration).plusMinutes(breakBetweenLessons))) {
                freeTimeSlots.add(nextFreeTimeSlotStart.toLocalTime());
                nextFreeTimeSlotStart = nextFreeTimeSlotStart.plusMinutes(lessonDuration).plusMinutes(breakBetweenLessons);
            }

            nextFreeTimeSlotStart = schedule.getLessonEnd().plusMinutes(breakBetweenLessons);
        }

        while (endTime.plusMinutes(lessonDuration).plusMinutes(breakBetweenLessons).isAfter(nextFreeTimeSlotStart)) {
            freeTimeSlots.add(nextFreeTimeSlotStart.toLocalTime());
            nextFreeTimeSlotStart = nextFreeTimeSlotStart
                    .plusMinutes(lessonDuration).plusMinutes(breakBetweenLessons);
        }

        return freeTimeSlots;
    }
}
