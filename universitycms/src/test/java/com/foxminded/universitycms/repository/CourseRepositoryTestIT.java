package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
class CourseRepositoryTestIT {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findAllByTeacherShouldReturnEmptyListWhenTeacherIsNull() {

        Teacher nullTeacher = null;

        List<Course> foundedCourses = courseRepository.findAllByTeacher(nullTeacher);

        assertTrue(foundedCourses.isEmpty());
    }

    @Test
    void findAllByTeacherShouldReturnEmptyListWhenRelationNotExist() {

        Teacher teacherNotRelatedToAnyCourse = Teacher.builder()
                .userId(999L)
                .firstName("Not")
                .lastName("Related")
                .email("not@related.com")
                .password("passwd")
                .department("Not Related")
                .build();

        List<Course> foundedCourses = courseRepository.findAllByTeacher(teacherNotRelatedToAnyCourse);

        assertTrue(foundedCourses.isEmpty());
    }

    @Test
    void findAllByTeacherShouldReturnListOfCoursesRelatedToTeacher() {

        Teacher teacherAlex = Teacher.builder()
                .userId(1L)
                .firstName("Alex")
                .lastName("Koperfild")
                .email("mail@mail.mail")
                .password("123@345")
                .build();

        List<Course> foundedCourses = courseRepository.findAllByTeacher(teacherAlex);

        assertNotNull(foundedCourses);
        assertEquals(2, foundedCourses.size());
    }
}
