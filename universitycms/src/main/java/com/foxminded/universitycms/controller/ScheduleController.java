package com.foxminded.universitycms.controller;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Day;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.dto.GroupDTO;
import com.foxminded.universitycms.entity.dto.ScheduleDTO;
import com.foxminded.universitycms.service.CourseService;
import com.foxminded.universitycms.service.GroupService;
import com.foxminded.universitycms.service.ScheduleService;
import com.foxminded.universitycms.service.TeacherService;
import com.foxminded.universitycms.service.impl.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final CourseService courseService;
    private final CalendarService calendarService;

    @GetMapping("/studentschedule")
    public String getStudentSchedule(Model model) {

        Long studentId = 81L;  // #TODO: should be real studentId

        Map<LocalDate, List<Schedule>> schedules = scheduleService.findScheduleByStudent(studentId, 30);
        List<List<Day>> weeks = calendarService.prepareCalendar(schedules);
        List<LocalDate> month = calendarService.prepareDates(30);
        model.addAttribute("month", month);
        model.addAttribute("weeks", weeks);
        return "studentschedule";
    }

    @GetMapping("/teacherschedule")
    public String getTeacherSchedule(Model model) {

        Long teacherId = 2L;  // #TODO: should be real teacherId
        List<Course> courses = new ArrayList<>(teacherService.findAllCoursesRelatedToTeacher(teacherId));

        Map<LocalDate, List<Schedule>> schedules = scheduleService.findScheduleByTeacher(teacherId, 30);
        List<List<Day>> weeks = calendarService.prepareCalendar(schedules);
        model.addAttribute("courses", courses);
        model.addAttribute("weeks", weeks);
        return "teacherschedule";
    }

    @ResponseBody
    @GetMapping("/getGroups")
    public List<GroupDTO> getGroupsRelatedToCourse(@RequestParam("course") long selectedCourse) {

        Course course = courseService.findById(selectedCourse);

        List<Group> groupsRelatedToCourse = groupService.findAllByCourse(course);

        return groupsRelatedToCourse.stream()
                .map(group -> new GroupDTO(group.getGroupId(), group.getGroupName()))
                .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/getTimeSlots/{selectedDate}/{teacherId}/{groupId}")
    public List<LocalTime> getAvailableTimes(
            @PathVariable LocalDate selectedDate, @PathVariable Long teacherId, @PathVariable long groupId) {

        Teacher teacher = teacherService.findById(teacherId);
        Group group = groupService.findById(groupId);

        return scheduleService.findFreeTimeForTeacherAndGroup(selectedDate, teacher, group);
    }

    @ResponseBody
    @GetMapping("/schedule/lesson/{scheduleId}")
    public Schedule getLesson(@PathVariable long scheduleId) {
        return scheduleService.findById(scheduleId);
    }

    @ResponseBody
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ScheduleDTO> addLesson(@RequestBody ScheduleDTO scheduleDTO) {

        scheduleService.save(scheduleDTO);
        return ResponseEntity.ok(scheduleDTO);
    }
}
