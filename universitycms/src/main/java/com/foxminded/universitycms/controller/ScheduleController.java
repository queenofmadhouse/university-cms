package com.foxminded.universitycms.controller;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.service.ScheduleService;
import com.foxminded.universitycms.service.StudentService;
import com.foxminded.universitycms.service.TeacherService;
import com.foxminded.universitycms.calendar.CalendarCreater;
import com.foxminded.universitycms.calendar.Day;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CalendarCreater calendarCreater;

    @GetMapping("/studentschedule")
    public String getStudentSchedule(Model model) {

        Group group = studentService.findById(81).getGroup();

        Map<LocalDate, List<Schedule>> schedules = scheduleService.getScheduleByGroup(group, 30);
        List<List<Day>> weeks = calendarCreater.prepareCalendar(schedules);
        model.addAttribute("weeks", weeks);
        return "studentschedule";
    }

    @GetMapping("/teacherschedule")
    public String getTeacherSchedule(Model model) {

        Teacher teacher = teacherService.findByUserId(1L);

        Map<LocalDate, List<Schedule>> schedules = scheduleService.getScheduleByTeacher(teacher, 30);
        List<List<Day>> weeks = calendarCreater.prepareCalendar(schedules);
        model.addAttribute("weeks", weeks);
        return "teacherschedule";
    }

    @ResponseBody
    @GetMapping("/schedule/lesson/{scheduleId}")
    public Schedule getLesson(@PathVariable long scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }
}

