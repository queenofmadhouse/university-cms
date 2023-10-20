package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import com.foxminded.universitycms.testojectsfactory.TestObjectFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
@Transactional
class TeacherServiceImplTestIT {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Test
    void findAllCoursesRelatedToTeacherShouldReturnListOfCoursesRelatedToTeacher() {

        Course courseMath = TestObjectFactory.createCourseMath();
        Course courseBiology = TestObjectFactory.createCourseBiology();

        Teacher teacherAlex = TestObjectFactory.createTeacherAlex();

        List<Course> foundCourses = teacherService.findAllCoursesRelatedToTeacher(teacherAlex.getEmail());

        assertNotNull(foundCourses);
        assertEquals(courseMath, foundCourses.get(0));
        assertEquals(courseBiology, foundCourses.get(1));
    }

    @Test
    void findByEmailShouldReturnTeacherRelatedToEmail() {

        Teacher teacherAlex = TestObjectFactory.createTeacherAlex();

        Teacher foundTeacher = teacherService.findByEmail(teacherAlex.getEmail());

        assertNotNull(foundTeacher);
        assertEquals(teacherAlex, foundTeacher);
    }
}
