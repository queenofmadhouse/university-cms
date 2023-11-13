package com.foxminded.universitycms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.universitycms.entity.Classroom;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.dto.ScheduleDTO;
import com.foxminded.universitycms.service.ClassroomService;
import com.foxminded.universitycms.service.CourseService;
import com.foxminded.universitycms.service.GroupService;
import com.foxminded.universitycms.service.ScheduleService;
import com.foxminded.universitycms.service.StudentService;
import com.foxminded.universitycms.service.TeacherService;
import com.foxminded.universitycms.service.impl.CalendarService;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import com.foxminded.universitycms.testojectsfactory.TestObjectFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@IntegrationTest
@AutoConfigureMockMvc
@Transactional
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @MockBean
    private ClassroomService classroomService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private CalendarService calendarService;

    @MockBean
    private CourseService courseService;

    @MockBean
    private GroupService groupService;

    @MockBean
    private Teacher teacher;

    @Test
    @WithMockUser(roles = "STUDENT")
    void getStudentScheduleShouldReturnStudentScheduleView() throws Exception {

        String studentEmail = "second@mail.com";

        when(studentService.findByEmail(studentEmail)).thenReturn(new Student());
        when(scheduleService.findScheduleByStudent(studentEmail, 30)).thenReturn(new HashMap<>());
        when(calendarService.prepareCalendar(new HashMap<>())).thenReturn(new ArrayList<>());
        when(calendarService.prepareDates(30)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/studentschedule"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentschedule"));
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void getTeacherScheduleShouldReturnTeacherScheduleView() throws Exception {

        String teacherEmail = "first@mail.com";

        when(teacherService.findByEmail(teacherEmail)).thenReturn(teacher);
        when(scheduleService.findScheduleByTeacher(teacherEmail, 30)).thenReturn(new HashMap<>());
        when(calendarService.prepareCalendar(new HashMap<>())).thenReturn(new ArrayList<>());
        when(teacher.getCourses()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/teacherschedule"))
                .andExpect(status().isOk())
                .andExpect(view().name("teacherschedule"));
    }

    @Test
    @WithMockUser(roles = "TEACHER")
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
    @WithMockUser(roles = "TEACHER", username = "first@mail.com")
    void getAvailableTimesShouldReturnListOfTimeSlots() throws Exception {

        LocalDate selectedDate = LocalDate.now();
        Teacher teacherAlex = TestObjectFactory.createTeacherAlex();
        Group group = new Group();

        when(teacherService.findByEmail(teacherAlex.getEmail())).thenReturn(teacherAlex);
        when(groupService.findById(1L)).thenReturn(group);
        when(scheduleService.findFreeTimeForTeacherAndGroup(selectedDate, teacher, group)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/getTimeSlots/{selectedDate}/{groupId}", selectedDate, 1L, 1L)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void getLessonShouldReturnSchedule() throws Exception {

        when(scheduleService.findById(1L)).thenReturn(new Schedule());

        mockMvc.perform(get("/lesson/{scheduleId}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "TEACHER", username = "first@mail.com")
    void addLessonShouldSaveScheduleToDB() throws Exception {

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setTeacher(1L);
        scheduleDTO.setCourse(1L);
        scheduleDTO.setGroup(1L);

        String teacherEmail = "first@mail.com";

        when(teacherService.findByEmail(teacherEmail)).thenReturn(new Teacher());
        when(courseService.findById(scheduleDTO.getCourse())).thenReturn(new Course());
        when(groupService.findById(scheduleDTO.getGroup())).thenReturn(new Group());

        mockMvc.perform(post("/add")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(scheduleDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "TEACHER", username = "first@mail.com")
    void getAvailableClassroomsShouldFindAvailableClassrooms() throws Exception {

        List<Classroom> listOfClassrooms = TestObjectFactory.createListOfClassrooms();
        LocalDateTime selectedDate = LocalDateTime.now();

        when(classroomService.findFreeClassrooms(selectedDate)).thenReturn(listOfClassrooms);

        mockMvc.perform(get("/getFreeClassrooms/{lessonStart}", selectedDate)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "TEACHER", username = "first@mail.com")
    void deleteLessonShouldDeleteLessonById() throws Exception {

        LocalDateTime existLessonStart = LocalDate.now().atStartOfDay().plusHours(8);
        LocalDateTime existLessonEnd = LocalDate.now().atStartOfDay().plusHours(8).plusMinutes(50);

        Schedule existLesson = TestObjectFactory.createExpectedSchedule(existLessonStart, existLessonEnd);

        mockMvc.perform(delete("/deleteLesson/{lessonId}", existLesson.getScheduleId())
                        .with(csrf()))
                .andExpect(status().isOk());

        Schedule foundScheduleAfterDeletingExistSchedule = scheduleService.findById(existLesson.getScheduleId());

        assertNull(foundScheduleAfterDeletingExistSchedule);
    }
}
