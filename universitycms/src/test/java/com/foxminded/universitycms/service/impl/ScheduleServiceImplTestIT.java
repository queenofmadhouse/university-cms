package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
class ScheduleServiceImplTestIT {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Test
    void getScheduleByGroupShouldReturnEmptyMapWhenScheduleForGroupNotExists() {

        Group notExistGroup = Group.builder()
                .groupId(999L)
                .groupName("Not Exists").build();

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.findScheduleByGroup(notExistGroup, 30);

        assertTrue(fundedSchedule.isEmpty());
    }

    @Test
    void getScheduleByTeacherShouldReturnEmptyMapWhenScheduleForTeacherNotExists() {

        Teacher notExistTeacher = Teacher.builder()
                .userId(999L)
                .firstName("Not Exist")
                .lastName("Not Exist")
                .email("not@exist.com")
                .password("null")
                .department("Not Exist")
                .build();

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.findScheduleByTeacher(notExistTeacher, 30);

        assertTrue(fundedSchedule.isEmpty());
    }

    @Test
    void getScheduleByGroupShouldReturnEmptyMapWhenGroupIsNull() {

        Group nullGroup = null;

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.findScheduleByGroup(nullGroup, 30);

        assertTrue(fundedSchedule.isEmpty());
    }

    @Test
    void getScheduleByTeacherShouldReturnEmptyMapWhenGroupIsNull() {

        Teacher nullTeacher = null;

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.findScheduleByTeacher(nullTeacher, 30);

        assertTrue(fundedSchedule.isEmpty());
    }

    @Test
    void getScheduleByGroupShouldReturnMapWithScheduleForGroup() {

        Teacher teacherAlex = Teacher.builder()
                .userId(1L)
                .firstName("Alex")
                .lastName("Kaplan")
                .email("mail@mail.com")
                .password("passwd")
                .department("Math Department")
                .build();

        Course courseBiology = Course.builder()
                .courseId(2L)
                .courseName("Biology")
                .courseDescription("Hard")
                .build();

        Group groupA5 = Group.builder()
                .groupId(1L)
                .groupName("A5").build();

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.findScheduleByGroup(groupA5, 30);

        assertNotNull(fundedSchedule);
        assertEquals(2, fundedSchedule.size());
        assertEquals(1, fundedSchedule.get(LocalDate.now()).size());
        assertEquals(1, fundedSchedule.get(LocalDate.now()).get(0).getScheduleId());
        assertEquals(teacherAlex, fundedSchedule.get(LocalDate.now()).get(0).getTeacher());
        assertEquals(groupA5, fundedSchedule.get(LocalDate.now()).get(0).getGroup());
        assertEquals(courseBiology, fundedSchedule.get(LocalDate.now()).get(0).getCourse());
        assertEquals(1, fundedSchedule.get(LocalDate.now().plusDays(5)).size());
        assertEquals(2, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0).getScheduleId());
        assertEquals(teacherAlex, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0).getTeacher());
        assertEquals(groupA5, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0).getGroup());
        assertEquals(courseBiology, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0).getCourse());
    }

    @Test
    void getScheduleByTeacherShouldReturnMapWithScheduleForTeacher() {

        Teacher teacherAlex = Teacher.builder()
                .userId(1L)
                .firstName("Alex")
                .lastName("Kaplan")
                .email("mail@mail.com")
                .password("passwd")
                .department("Math Department")
                .build();

        Course courseBiology = Course.builder()
                .courseId(2L)
                .courseName("Biology")
                .courseDescription("Hard")
                .build();

        Group groupA5 = Group.builder()
                .groupId(1L)
                .groupName("A5").build();

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.findScheduleByTeacher(teacherAlex, 30);

        assertNotNull(fundedSchedule);
        assertEquals(2, fundedSchedule.size());
        assertEquals(1, fundedSchedule.get(LocalDate.now()).size());
        assertEquals(1, fundedSchedule.get(LocalDate.now()).get(0).getScheduleId());
        assertEquals(teacherAlex, fundedSchedule.get(LocalDate.now()).get(0).getTeacher());
        assertEquals(groupA5, fundedSchedule.get(LocalDate.now()).get(0).getGroup());
        assertEquals(courseBiology, fundedSchedule.get(LocalDate.now()).get(0).getCourse());
        assertEquals(1, fundedSchedule.get(LocalDate.now().plusDays(5)).size());
        assertEquals(2, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0).getScheduleId());
        assertEquals(teacherAlex, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0).getTeacher());
        assertEquals(groupA5, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0).getGroup());
        assertEquals(courseBiology, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0).getCourse());
    }
}
