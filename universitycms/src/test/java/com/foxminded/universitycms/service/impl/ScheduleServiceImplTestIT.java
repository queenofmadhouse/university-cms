package com.foxminded.universitycms.service.impl;

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

@IntegrationTest
class ScheduleServiceImplTestIT {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Test
    void getScheduleByGroupShouldReturnMapWithScheduleForGroup() {

        Group groupA5 = Group.builder()
                .groupId(1L)
                .groupName("A5").build();

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.getScheduleByGroup(groupA5, 30);

        assertNotNull(fundedSchedule);
        assertEquals(2, fundedSchedule.size());
        assertEquals(1, fundedSchedule.get(LocalDate.now()).size());
        assertEquals(1, fundedSchedule.get(LocalDate.now().plusDays(5)).size());
    }

    @Test
    void getScheduleByTeacherShouldReturnMapWithScheduleForTeacher() {

        Teacher teacherAlex = Teacher.builder()
                .userId(1L)
                .firstName("Alex")
                .lastName("Koperfild")
                .email("mail@mail.mail")
                .password("123@345")
                .build();

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.getScheduleByTeacher(teacherAlex, 30);

        assertNotNull(fundedSchedule);
        assertEquals(2, fundedSchedule.size());
        assertEquals(1, fundedSchedule.get(LocalDate.now()).size());
        assertEquals(1, fundedSchedule.get(LocalDate.now().plusDays(5)).size());
    }
}
