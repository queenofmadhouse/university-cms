package com.foxminded.universitycms.controller;

import com.foxminded.universitycms.entity.Classroom;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Day;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.dto.ClassroomDTO;
import com.foxminded.universitycms.entity.dto.GroupDTO;
import com.foxminded.universitycms.entity.dto.ScheduleDTO;
import com.foxminded.universitycms.service.ClassroomService;
import com.foxminded.universitycms.service.CourseService;
import com.foxminded.universitycms.service.GroupService;
import com.foxminded.universitycms.service.ScheduleService;
import com.foxminded.universitycms.service.TeacherService;
import com.foxminded.universitycms.service.impl.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final CourseService courseService;
    private final ClassroomService classroomService;
    private final CalendarService calendarService;
    @Value("${app.constants.calendar.amount-of-days}")
    private int amountOfDaysInMonth;

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/studentschedule")
    public String getStudentSchedule(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentEmail = authentication.getName();

        log.info("Trying to get schedule for student with email: " + studentEmail);

        Map<LocalDate, List<Schedule>> schedules = scheduleService.findScheduleByStudent(studentEmail,
                amountOfDaysInMonth);
        List<List<Day>> weeks = calendarService.prepareCalendar(schedules);
        List<LocalDate> month = calendarService.prepareDates(30);
        model.addAttribute("month", month);
        model.addAttribute("weeks", weeks);
        return "studentschedule";
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/teacherschedule")
    public String getTeacherSchedule(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String teacherEmail = authentication.getName();

        log.info("Trying to get schedule for teacher with email: {}", teacherEmail);

        List<Course> courses = new ArrayList<>(teacherService.findAllCoursesRelatedToTeacher(teacherEmail));

        Map<LocalDate, List<Schedule>> schedules = scheduleService.findScheduleByTeacher(teacherEmail,
                amountOfDaysInMonth);
        List<List<Day>> weeks = calendarService.prepareCalendar(schedules);
        model.addAttribute("courses", courses);
        model.addAttribute("weeks", weeks);
        return "teacherschedule";
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @ResponseBody
    @GetMapping("/getGroups")
    public List<GroupDTO> getGroupsRelatedToCourse(@RequestParam("course") long selectedCourse) {

        log.info("Trying to find groups related to course with id: {} ", selectedCourse);

        Course course = courseService.findById(selectedCourse);

        List<Group> groupsRelatedToCourse = groupService.findAllByCourse(course);

        log.info("Found groups related to course: " + groupsRelatedToCourse);

        return groupsRelatedToCourse.stream()
                .map(group -> new GroupDTO(group.getGroupId(), group.getGroupName()))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @ResponseBody
    @GetMapping("/getTimeSlots/{selectedDate}/{groupId}")
    public List<LocalTime> getAvailableTimes(
            @PathVariable LocalDate selectedDate, @PathVariable long groupId) {

        log.info("Trying to find free time slots for group with id: {}", groupId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String teacherEmail = authentication.getName();

        Teacher teacher = teacherService.findByEmail(teacherEmail);
        Group group = groupService.findById(groupId);

        return scheduleService.findFreeTimeForTeacherAndGroup(selectedDate, teacher, group);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @ResponseBody
    @GetMapping("/getFreeClassrooms/{lessonStart}")
    public List<ClassroomDTO> getAvailableClassrooms(@PathVariable LocalDateTime lessonStart) {

        log.info("Trying to find free classrooms for lesson, that will starts at: {}", lessonStart);

        List<Classroom> foundFreeClassrooms = classroomService.findFreeClassrooms(lessonStart);

        log.info("Found free classrooms: {}", foundFreeClassrooms.toString());

        return foundFreeClassrooms.stream()
                .map(classroom -> new ClassroomDTO(classroom.getClassroomId()))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @ResponseBody
    @GetMapping("/lesson/{scheduleId}")
    public Schedule getLesson(@PathVariable long scheduleId) {

        log.info("Trying to find lesson by id: {}", scheduleId);

        return scheduleService.findById(scheduleId);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @ResponseBody
    @DeleteMapping("/deleteLesson/{lessonId}")
    public void deleteLesson(@PathVariable long lessonId) {

        log.info("Trying to delete lesson with id: {}", lessonId);

        scheduleService.deleteById(lessonId);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @ResponseBody
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ScheduleDTO> addLesson(@RequestBody ScheduleDTO scheduleDTO) {

        log.info("Received schedule data: {}", scheduleDTO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String teacherEmail = authentication.getName();

        Teacher teacher = teacherService.findByEmail(teacherEmail);

        scheduleDTO.setTeacher(teacher.getUserId());

        log.info("Trying to save lesson: {}", scheduleDTO.toString());

        scheduleService.save(scheduleDTO);

        log.info("Lesson successfully saved, returning response: {}", scheduleDTO);

        return ResponseEntity.ok(scheduleDTO);
    }
}
