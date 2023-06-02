package com.foxminded.universitycms.controller;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.service.CourseService;
import com.foxminded.universitycms.service.GroupService;
import com.foxminded.universitycms.service.ScheduleService;
import com.foxminded.universitycms.service.StudentService;
import com.foxminded.universitycms.service.TeacherService;
import com.foxminded.universitycms.service.impl.CalendarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = ScheduleController.class)
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private GroupService groupService;

    @MockBean
    private CourseService courseService;

    @MockBean
    private CalendarService calendarService;

    @MockBean
    private Teacher teacher;

    @Test
    void testGetStudentSchedule() throws Exception {
        Group group = new Group();
        when(studentService.findById(81)).thenReturn(new Student());
        when(scheduleService.getScheduleByGroup(group, 30)).thenReturn(new HashMap<>());
        when(calendarService.prepareCalendar(new HashMap<>())).thenReturn(new ArrayList<>());
        when(calendarService.prepareDates(30)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/studentschedule"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentschedule"));
    }

    @Test
    void testGetTeacherSchedule() throws Exception {
        when(teacherService.findByUserId(2L)).thenReturn(teacher);
        when(scheduleService.getScheduleByTeacher(teacher, 30)).thenReturn(new HashMap<>());
        when(calendarService.prepareCalendar(new HashMap<>())).thenReturn(new ArrayList<>());
        when(teacher.getCourses()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/teacherschedule"))
                .andExpect(status().isOk())
                .andExpect(view().name("teacherschedule"));
    }
}
