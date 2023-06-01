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
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.springframework.stereotype.Service;

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
    private final Flyway flyway;


    @PostConstruct
    @Override
    public void generateAllData() {
        if (databaseIsEmpty()) {
            List<Course> courses = generateCourse();
            List<Group> groups = generateGroup(courses);
            List<Teacher> teachers = generateTeachers(courses);
            List<Student> students = generateStudents(groups);
            List<Schedule> schedules = generateSchedules(groups);

            courseService.saveAll(courses);
            groupService.saveAll(groups);
            teacherService.saveAll(teachers);
            studentService.saveAll(students);
            scheduleService.saveAll(schedules);
        }
    }

    private List<Course> generateCourse() {
        return courseGenerator.generateData();
    }

    private List<Group> generateGroup(List<Course> courses) {
        return groupGenerator.generateData(courses);
    }

    private List<Teacher> generateTeachers(List<Course> courses) {
        return teacherGenerator.generateData(courses);
    }

    private List<Student> generateStudents(List<Group> groups) {
        return studentsGenerator.generateData(groups);
    }

    private List<Schedule> generateSchedules(List<Group> groups) {
        return scheduleGenerator.generateData(groups);
    }

    private boolean databaseIsEmpty() {
        MigrationInfo[] migrationInfos = flyway.info().all();
        return migrationInfos.length == 0;
    }
}
