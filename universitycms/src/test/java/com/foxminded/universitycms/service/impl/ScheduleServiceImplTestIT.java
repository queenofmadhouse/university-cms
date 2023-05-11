package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.repository.CourseRepository;
import com.foxminded.universitycms.repository.GroupRepository;
import com.foxminded.universitycms.repository.ScheduleRepository;
import com.foxminded.universitycms.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        ScheduleRepository.class,
        ScheduleServiceImpl.class,
        CourseRepository.class,
        GroupRepository.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/db/сreate_schema_and_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
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
    void findScheduleForMonthForTeacherShouldReturnListWithSize30() {

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

        Map<LocalDate,List<Schedule>> fundedSchedule = scheduleService.findScheduleForMonthForGroup(groupA5);
        assertEquals(30, fundedSchedule.size());

        System.out.println(fundedSchedule);
        assertEquals(1, fundedSchedule.get(LocalDate.now()).size());
        assertEquals(firstTestSchedule, fundedSchedule.get(LocalDate.now()).get(0));
        assertEquals(1, fundedSchedule.get(LocalDate.now().plusDays(5)).size());
        assertEquals(secondTestSchedule, fundedSchedule.get(LocalDate.now().plusDays(5)).get(0));
    }
}
