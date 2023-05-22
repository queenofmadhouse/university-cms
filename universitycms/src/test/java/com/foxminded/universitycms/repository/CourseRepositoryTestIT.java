package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
class CourseRepositoryTestIT {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void findAllByTeacherShouldReturnListOfCoursesRelatedToTeacher() {

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

        teacherRepository.save(teacherAlex);
        courseRepository.save(courseMath);
        courseRepository.save(courseBiology);

        teacherRepository.addTeacherToCourse(teacherAlex, courseMath);
        teacherRepository.addTeacherToCourse(teacherAlex, courseBiology);

        List<Course> foundedCourses = courseRepository.findAllByTeacher(teacherAlex);

        assertNotNull(foundedCourses);
        assertEquals(2, foundedCourses.size());
        assertEquals(courseMath, foundedCourses.get(0));
        assertEquals(courseBiology, foundedCourses.get(1));
    }
}
