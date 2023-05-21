package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.repository.CourseRepository;
import com.foxminded.universitycms.repository.GroupRepository;
import com.foxminded.universitycms.repository.ScheduleRepository;
import com.foxminded.universitycms.repository.TeacherRepository;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
class ScheduleServiceImplTestIT {

    @Autowired
    ScheduleServiceImpl scheduleService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void getScheduleByGroupShouldReturnMapWithScheduleForGroup() {

        LocalDateTime firstDateStart = LocalDateTime.now();
        LocalDateTime firstDateEnd = firstDateStart.plusHours(1);

        LocalDateTime secondDateStart = firstDateStart.plusDays(5);
        LocalDateTime secondDateEnd = secondDateStart.plusHours(1);

        Teacher teacherAlex = Teacher.builder()
                .userId(1L)
                .firstName("Alex")
                .lastName("Koperfild")
                .email("mail@mail.mail")
                .password("123@345")
                .build();

        Course courseMath = Course.builder()
                .courseId(1L)
                .courseName("Math")
                .courseDescription("Hard")
                .build();

        Course courseBiology = Course.builder()
                .courseId(2L)
                .courseName("Biology")
                .courseDescription("Hard")
                .build();

        Group groupA5 = Group.builder()
                .groupId(1L)
                .groupName("A5").build();

        teacherRepository.save(teacherAlex);
        courseRepository.save(courseMath);
        courseRepository.save(courseBiology);
        groupRepository.save(groupA5);

        Schedule firstTestSchedule = Schedule.builder()
                .scheduleId(1L)
                .teacher(teacherAlex)
                .course(courseBiology)
                .group(groupA5)
                .lessonStart(firstDateStart)
                .lessonEnd(firstDateEnd)
                .lessonDescription("Description")
                .build();

        Schedule secondTestSchedule = Schedule.builder()
                .scheduleId(2L)
                .teacher(teacherAlex)
                .course(courseBiology)
                .group(groupA5)
                .lessonStart(secondDateStart)
                .lessonEnd(secondDateEnd)
                .lessonDescription("Description")
                .build();

        scheduleRepository.save(firstTestSchedule);
        scheduleRepository.save(secondTestSchedule);

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.getScheduleByGroup(groupA5, 30);

        assertEquals(2, fundedSchedule.size());
        assertEquals(1, fundedSchedule.get(LocalDate.now()).size());
        assertEquals(firstTestSchedule, fundedSchedule.get(LocalDate.now()).get(0));
        assertEquals(1, fundedSchedule.get(LocalDate.now().plusDays(5)).size());
        assertEquals(secondTestSchedule, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0));
    }

    @Test
    void getScheduleByTeacherShouldReturnMapWithScheduleForTeacher() {

        LocalDateTime firstDateStart = LocalDateTime.now();
        LocalDateTime firstDateEnd = firstDateStart.plusHours(1);

        LocalDateTime secondDateStart = firstDateStart.plusDays(5);
        LocalDateTime secondDateEnd = secondDateStart.plusHours(1);

        Teacher teacherAlex = Teacher.builder()
                .userId(1L)
                .firstName("Alex")
                .lastName("Koperfild")
                .email("mail@mail.mail")
                .password("123@345")
                .build();

        Course courseMath = Course.builder()
                .courseId(1L)
                .courseName("Math")
                .courseDescription("Hard")
                .build();

        Course courseBiology = Course.builder()
                .courseId(2L)
                .courseName("Biology")
                .courseDescription("Hard")
                .build();

        Group groupA5 = Group.builder()
                .groupId(1L)
                .groupName("A5").build();

        teacherRepository.save(teacherAlex);
        courseRepository.save(courseMath);
        courseRepository.save(courseBiology);
        groupRepository.save(groupA5);

        Schedule firstTestSchedule = Schedule.builder()
                .scheduleId(1L)
                .teacher(teacherAlex)
                .course(courseBiology)
                .group(groupA5)
                .lessonStart(firstDateStart)
                .lessonEnd(firstDateEnd)
                .lessonDescription("Description")
                .build();

        Schedule secondTestSchedule = Schedule.builder()
                .scheduleId(2L)
                .teacher(teacherAlex)
                .course(courseBiology)
                .group(groupA5)
                .lessonStart(secondDateStart)
                .lessonEnd(secondDateEnd)
                .lessonDescription("Description")
                .build();

        scheduleRepository.save(firstTestSchedule);
        scheduleRepository.save(secondTestSchedule);

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.getScheduleByTeacher(teacherAlex, 30);

        assertEquals(2, fundedSchedule.size());
        assertEquals(1, fundedSchedule.get(LocalDate.now()).size());
        assertEquals(firstTestSchedule, fundedSchedule.get(LocalDate.now()).get(0));
        assertEquals(1, fundedSchedule.get(LocalDate.now().plusDays(5)).size());
        assertEquals(secondTestSchedule, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0));
    }
}
