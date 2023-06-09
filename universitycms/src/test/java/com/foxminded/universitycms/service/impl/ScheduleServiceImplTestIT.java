package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.exception.DatabaseRuntimeException;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
class ScheduleServiceImplTestIT {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Test
    void getScheduleByGroupShouldThrowDataBaseRuntimeExceptionWhenScheduleForGroupNotExists() {

        Group notExistGroup = Group.builder()
                .groupId(999L)
                .groupName("Not Exists").build();

        assertThrows(DatabaseRuntimeException.class, () ->
                scheduleService.findScheduleByStudent(notExistGroup.getGroupId(), 30));
    }

    @Test
    void getScheduleByTeacherShouldThrowDataBaseRuntimeExceptionWhenScheduleForTeacherNotExists() {

        Teacher notExistTeacher = Teacher.builder()
                .userId(999L)
                .firstName("Not Exist")
                .lastName("Not Exist")
                .email("not@exist.com")
                .password("null")
                .department("Not Exist")
                .build();

        assertThrows(DatabaseRuntimeException.class, () ->
                scheduleService.findScheduleByTeacher(notExistTeacher.getUserId(), 30));
    }

//    @Test
//    void getScheduleByGroupShouldThrowDataBaseRuntimeExceptionWhenGroupIsNull() {
//
//        assertThrows(DatabaseRuntimeException.class, () ->
//                scheduleService.findScheduleByStudent(null, 30));
//    }

//    @Test
//    void getScheduleByTeacherShouldThrowDataBaseRuntimeExceptionWhenGroupIsNull() {
//
//        Teacher nullTeacher = null;
//
//        assertThrows(DatabaseRuntimeException.class, () ->
//                scheduleService.findScheduleByTeacher(nullTeacher.getUserId(), 30));
//    }

    @Test
    void getScheduleByGroupShouldReturnMapWithScheduleForGroup() {

        Teacher teacherAlex = Teacher.builder()
                .userId(1L)
                .firstName("Alex")
                .lastName("Kaplan")
                .email("first@mail.com")
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

        Student studentBoyana = Student.builder()
                .userId(2L)
                .firstName("Alex")
                .lastName("Kaplan")
                .email("second@mail.com")
                .password("passwd")
                .group(groupA5)
                .build();

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService
                .findScheduleByStudent(studentBoyana.getUserId(), 30);

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
                .email("first@mail.com")
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

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.findScheduleByTeacher(teacherAlex.getUserId(), 30);

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
