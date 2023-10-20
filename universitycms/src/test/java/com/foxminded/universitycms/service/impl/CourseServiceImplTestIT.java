package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.exception.DatabaseRuntimeException;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import com.foxminded.universitycms.testojectsfactory.TestObjectFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@IntegrationTest
@Transactional
class CourseServiceImplTestIT {

    @Autowired
    private CourseServiceImpl courseService;

    @Test
    void findByIdShouldReturnCourseRelatedToId() {

        Course courseBiology = TestObjectFactory.createCourseBiology();

        Course foundCourse = courseService.findById(courseBiology.getCourseId());

        assertNotNull(foundCourse);
        assertEquals(courseBiology, foundCourse);
    }

    @Test
    void findByIdShouldThrownDatabaseRuntimeExceptionWhenCantFindCourseById() {

        Course notExistCourse = TestObjectFactory.createNotExistCourse();

        assertThrows(DatabaseRuntimeException.class,
                () -> courseService.findById(notExistCourse.getCourseId()));
    }
}
