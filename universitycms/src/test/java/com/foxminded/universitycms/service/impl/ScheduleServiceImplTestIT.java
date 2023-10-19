package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.dto.ScheduleDTO;
import com.foxminded.universitycms.exception.DatabaseRuntimeException;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import com.foxminded.universitycms.testojectsfactory.TestObjectFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@IntegrationTest
@Transactional
class ScheduleServiceImplTestIT {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Test
    void getScheduleByTeacherShouldThrowDataBaseRuntimeExceptionWhenScheduleForTeacherNotExists() {

        Teacher notExistTeacher = TestObjectFactory.createNotExistTeacher();

        assertThrows(DatabaseRuntimeException.class, () ->
                scheduleService.findScheduleByTeacher(notExistTeacher.getEmail(), 30));
    }

    @Test
    void getScheduleByGroupShouldThrowDataBaseRuntimeExceptionWhenGroupIsNull() {

        assertThrows(DatabaseRuntimeException.class, () ->
                scheduleService.findScheduleByStudent(null, 30));
    }

    @Test
    void getScheduleByGroupShouldReturnMapWithScheduleForGroup() {

        Teacher teacherAlex = TestObjectFactory.createTeacherAlex();
        Course courseBiology = TestObjectFactory.createCourseBiology();
        Group groupA5 = TestObjectFactory.createGroupA5();
        Student studentBoyana = TestObjectFactory.createStudentBoyana();

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService
                .findScheduleByStudent(studentBoyana.getEmail(), 30);

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

        Teacher teacherAlex = TestObjectFactory.createTeacherAlex();
        Course courseBiology = TestObjectFactory.createCourseBiology();
        Group groupA5 = TestObjectFactory.createGroupA5();

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.findScheduleByTeacher(teacherAlex.getEmail(), 30);

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
    void findByIdShouldThrowDatabaseRuntimeExceptionWhenIdNotExists() {

        long nonExistentId = 999L;

        assertThrows(DatabaseRuntimeException.class, () -> scheduleService.findById(nonExistentId));
    }

    @Test
    void deleteByIdShouldDeleteScheduleWhenIdExists() {

        long id = 1;

        scheduleService.deleteById(id);

        assertThrows(DatabaseRuntimeException.class, () -> scheduleService.findById(id));
    }

    @Test
    void findByIdShouldReturnScheduleById() {

        LocalDateTime lessonStart = LocalDate.now().atStartOfDay().plusHours(8);
        LocalDateTime lessonEnd = LocalDate.now().atStartOfDay().plusHours(8).plusMinutes(50);

        Schedule expextedSchedule = TestObjectFactory.createExpectedSchedule(lessonStart, lessonEnd);

        Schedule foundSchedule = scheduleService.findById(1L);

        assertNotNull(foundSchedule);
        assertEquals(expextedSchedule.getScheduleId(), foundSchedule.getScheduleId());
        assertEquals(expextedSchedule.getTeacher().getUserId(), foundSchedule.getTeacher().getUserId());
        assertEquals(expextedSchedule.getGroup().getGroupId(), foundSchedule.getGroup().getGroupId());
        assertEquals(expextedSchedule.getCourse().getCourseId(), foundSchedule.getCourse().getCourseId());
        assertEquals(expextedSchedule.getClassroomId().getClassroomId(), foundSchedule.getClassroomId().getClassroomId());
        assertEquals(expextedSchedule.getLessonStart(), foundSchedule.getLessonStart());
        assertEquals(expextedSchedule.getLessonEnd(), foundSchedule.getLessonEnd());
        assertEquals(expextedSchedule.getLessonDescription(), foundSchedule.getLessonDescription());
    }

    @Test
    @Sql(scripts = "classpath:db/reset_schedule.sql")
    void saveShouldSaveScheduleToDatabase() {

        LocalDateTime lessonStart = LocalDate.now().atStartOfDay().plusHours(10);
        LocalDateTime lessonEnd = LocalDate.now().atStartOfDay().plusHours(10).plusMinutes(50);

        Schedule expectedSchedule = TestObjectFactory.createExpectedSchedule(lessonStart, lessonEnd);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setTeacher(1L);
        scheduleDTO.setGroup(1L);
        scheduleDTO.setCourse(2L);
        scheduleDTO.setClassroom(1L);
        scheduleDTO.setLessonStart(lessonStart);
        scheduleDTO.setLessonEnd(lessonEnd);
        scheduleDTO.setLessonDescription("Description");

        scheduleService.save(scheduleDTO);

        Schedule foundSchedule = scheduleService.findById(1L);

        assertNotNull(foundSchedule);
        assertEquals(expectedSchedule.getScheduleId(), foundSchedule.getScheduleId());
        assertEquals(expectedSchedule.getTeacher().getUserId(), foundSchedule.getTeacher().getUserId());
        assertEquals(expectedSchedule.getGroup().getGroupId(), foundSchedule.getGroup().getGroupId());
        assertEquals(expectedSchedule.getCourse().getCourseId(), foundSchedule.getCourse().getCourseId());
        assertEquals(expectedSchedule.getClassroomId().getClassroomId(), foundSchedule.getClassroomId().getClassroomId());
        assertEquals(expectedSchedule.getLessonStart(), foundSchedule.getLessonStart());
        assertEquals(expectedSchedule.getLessonEnd(), foundSchedule.getLessonEnd());
        assertEquals(expectedSchedule.getLessonDescription(), foundSchedule.getLessonDescription());
    }

    @Test
    void findFreeTimeForTeacherAndGroupShouldReturnAvailableSlots() {

        Teacher teacherAlex = TestObjectFactory.createTeacherAlex();

        Group groupA5 = Group.builder()
                .groupId(1L)
                .groupName("A5").build();

        LocalDate date = LocalDate.now();

        List<LocalTime> freeTimeSlots = scheduleService.findFreeTimeForTeacherAndGroup(date, teacherAlex, groupA5);

        assertNotNull(freeTimeSlots);
    }

//    TODO: add a test to achieve 100% coverage
}
