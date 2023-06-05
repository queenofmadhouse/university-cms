package com.foxminded.universitycms.datagenerator.impl;

import com.foxminded.universitycms.datagenerator.CourseGenerator;
import com.foxminded.universitycms.datagenerator.DataGenerator;
import com.foxminded.universitycms.datagenerator.GroupGenerator;
import com.foxminded.universitycms.datagenerator.ScheduleGenerator;
import com.foxminded.universitycms.datagenerator.StudentsGenerator;
import com.foxminded.universitycms.datagenerator.TeacherGenerator;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.service.CourseService;
import com.foxminded.universitycms.service.GroupService;
import com.foxminded.universitycms.service.ScheduleService;
import com.foxminded.universitycms.service.StudentService;
import com.foxminded.universitycms.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DataGeneratorImpl implements DataGenerator {

    private final CourseGenerator courseGenerator;
    private final GroupGenerator groupGenerator;
    private final TeacherGenerator teacherGenerator;
    private final StudentsGenerator studentsGenerator;
    private final ScheduleGenerator scheduleGenerator;
    private final CourseService courseService;
    private final GroupService groupService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ScheduleService scheduleService;

    @PostConstruct
    @Transactional
    @Override
    public void generateAllData() {

        if (databaseIsEmpty()) {

            generateCourse();
            generateGroup();
            generateTeachers();
            generateStudents();
            generateSchedules();
        }
    }

    @Transactional
    public void generateCourse() {
        List<Course> courses = courseGenerator.generateData();
        courseService.saveAll(courses);
    }

    @Transactional
    public void generateGroup() {
        List<Course> courses = courseService.findAll();
        List<Group> groups = groupGenerator.generateData(courses);
        groupService.saveAll(groups);
    }

    @Transactional
    public void generateTeachers() {
        List<Course> courses = courseService.findAll();
        List<Teacher> teachers = teacherGenerator.generateData(courses);
        teacherService.saveAll(teachers);
    }

    @Transactional
    public void generateStudents() {
        List<Group> groups = groupService.findAll();
        List<Student> students = studentsGenerator.generateData(groups);
        studentService.saveAll(students);
    }

    @Transactional
    public void generateSchedules() {
        List<Group> groups = groupService.findAll();
        List<Schedule> schedules = scheduleGenerator.generateData(groups);
        scheduleService.saveAll(schedules);
    }

    private boolean databaseIsEmpty() {

        boolean isCoursesEmpty = courseService.isTableEmpty();
        boolean isGroupsEmpty = groupService.isTableEmpty();
        boolean isTeachersEmpty = teacherService.isTableEmpty();
        boolean isStudentsEmpty = studentService.isTableEmpty();
        boolean isScheduleEmpty = scheduleService.isTableEmpty();

        return isCoursesEmpty &&
                isGroupsEmpty &&
                isTeachersEmpty &&
                isStudentsEmpty &&
                isScheduleEmpty;
    }
}
