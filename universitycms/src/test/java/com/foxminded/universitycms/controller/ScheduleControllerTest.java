package com.foxminded.universitycms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.dto.ScheduleDTO;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    void getStudentScheduleShouldReturnStudentScheduleView() throws Exception {

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
    void getTeacherScheduleShouldReturnTeacherScheduleView() throws Exception {

        when(teacherService.findByUserId(2L)).thenReturn(teacher);
        when(scheduleService.getScheduleByTeacher(teacher, 30)).thenReturn(new HashMap<>());
        when(calendarService.prepareCalendar(new HashMap<>())).thenReturn(new ArrayList<>());
        when(teacher.getCourses()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/teacherschedule"))
                .andExpect(status().isOk())
                .andExpect(view().name("teacherschedule"));
    }

    @Test
    void getGroupsRelatedToCourseShouldReturnListOfGroups() throws Exception {
        Course course = new Course();
        when(courseService.findById(1L)).thenReturn(course);

        Group group = new Group();
        group.setGroupId(1L);
        group.setGroupName("Test Group");

        List<Group> groups = Arrays.asList(group);
        when(groupService.findAllByCourse(course)).thenReturn(groups);

        mockMvc.perform(get("/getGroups").param("course", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Test Group\"}]"));
    }

    @Test
    void getAvailableTimesShouldReturnListOfTimeSlots() throws Exception {

        LocalDate selectedDate = LocalDate.now();
        Teacher teacher = new Teacher();
        Group group = new Group();

        when(teacherService.findByUserId(1L)).thenReturn(teacher);
        when(groupService.findById(1L)).thenReturn(group);
        when(scheduleService.findFreeTimeForTeacherAndGroup(selectedDate, teacher, group)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/getTimeSlots/{selectedDate}/{teacherId}/{groupId}", selectedDate, 1L, 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getLessonShouldReturnSchedule() throws Exception {

        when(scheduleService.getScheduleById(1L)).thenReturn(new Schedule());

        mockMvc.perform(get("/schedule/lesson/{scheduleId}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void addLessonShouldSaveScheduleToDB() throws Exception {

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setTeacher(1L);
        scheduleDTO.setCourse(1L);
        scheduleDTO.setGroup(1L);

        when(teacherService.findByUserId(scheduleDTO.getTeacher())).thenReturn(new Teacher());
        when(courseService.findById(scheduleDTO.getCourse())).thenReturn(new Course());
        when(groupService.findById(scheduleDTO.getGroup())).thenReturn(new Group());

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(scheduleDTO)))
                .andExpect(status().isOk());
    }
}
