package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.repository.GroupRepository;
import com.foxminded.universitycms.repository.TeacherRepository;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
@Sql(
        scripts = "/sql/schedule-test-data.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class ScheduleServiceImplTestIT {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void getScheduleByGroupShouldReturnMapWithScheduleForGroup() {

        Group foundGroup = groupRepository.findByGroupId(1L).orElse(null);

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.getScheduleByGroup(foundGroup, 30);

        assertNotNull(fundedSchedule);
        assertEquals(2, fundedSchedule.size());
        assertEquals(1, fundedSchedule.get(LocalDate.now()).size());
        assertEquals(1, fundedSchedule.get(LocalDate.now().plusDays(5)).size());
    }

    @Test
    void getScheduleByTeacherShouldReturnMapWithScheduleForTeacher() {

        Teacher foundTeacher = teacherRepository.findByUserId(1L).orElse(null);

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.getScheduleByTeacher(foundTeacher, 30);

        assertNotNull(fundedSchedule);
        assertEquals(2, fundedSchedule.size());
        assertEquals(1, fundedSchedule.get(LocalDate.now()).size());
        assertEquals(1, fundedSchedule.get(LocalDate.now().plusDays(5)).size());
    }
}
