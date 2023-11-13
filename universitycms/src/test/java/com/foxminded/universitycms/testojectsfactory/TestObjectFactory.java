package com.foxminded.universitycms.testojectsfactory;

import com.foxminded.universitycms.entity.Classroom;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestObjectFactory {

    public static Teacher createTeacherAlex() {

        return Teacher.builder()
                .userId(1L)
                .firstName("Alex")
                .lastName("Kaplan")
                .email("first@mail.com")
                .password("passwd")
                .department("Math Department")
                .build();
    }

    public static Teacher createNotExistTeacher() {

        return Teacher.builder()
                .userId(999L)
                .firstName("Not Exist")
                .lastName("Not Exist")
                .email("not@exist.com")
                .password("null")
                .department("Not Exist")
                .build();
    }

    public static Student createStudentBoyana() {

        return Student.builder()
                .userId(2L)
                .firstName("Alex")
                .lastName("Kaplan")
                .email("second@mail.com")
                .password("passwd")
                .group(createGroupA5())
                .build();
    }

    public static Course createCourseBiology() {

        return Course.builder()
                .courseId(2L)
                .courseName("Biology")
                .courseDescription("Hard")
                .build();
    }

    public static Course createCourseMath() {

        return Course.builder()
                .courseId(1L)
                .courseName("Math")
                .courseDescription("Hard")
                .build();
    }

    public static Course createNotExistCourse() {

        return Course.builder()
                .courseId(100000L)
                .courseName("Not Exist")
                .courseDescription("Not Exist")
                .build();
    }

    public static Group createGroupA5() {

        return Group.builder()
                .groupId(1L)
                .groupName("A5").build();
    }

    public static Group createGroupB3() {

        return Group.builder()
                .groupId(2L)
                .groupName("B3")
                .build();
    }

    public static Group createNotExistGroup() {

        return Group.builder()
                .groupId(100000L)
                .groupName("Not Exist")
                .build();
    }

    public static Classroom createFirstClassroom() {

        return Classroom.builder()
                .classroomId(1L)
                .build();
    }

    public static Schedule createExpectedSchedule(LocalDateTime lessonStart, LocalDateTime lessonEnd) {

        return Schedule.builder()
                .scheduleId(1L)
                .teacher(createTeacherAlex())
                .group(createGroupA5())
                .course(createCourseBiology())
                .classroomId(createFirstClassroom())
                .lessonDescription("Description")
                .lessonStart(lessonStart)
                .lessonEnd(lessonEnd)
                .build();
    }

    public static List<Classroom> createListOfClassrooms() {

        List<Classroom> listOfClassrooms = new ArrayList<>();

        Classroom firstClassroom = Classroom.builder()
                .classroomId(1L)
                .build();
        Classroom secondClassroom = Classroom.builder()
                .classroomId(2L)
                .build();

        listOfClassrooms.add(firstClassroom);
        listOfClassrooms.add(secondClassroom);

        return listOfClassrooms;
    }
}
