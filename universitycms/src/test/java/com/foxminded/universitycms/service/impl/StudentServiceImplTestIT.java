package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import com.foxminded.universitycms.testojectsfactory.TestObjectFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
@Transactional
class StudentServiceImplTestIT {

    @Autowired
    private StudentServiceImpl studentService;

    @Test
    void findByEmailShouldReturnStudentRelatedToEmail() {

        Student studentBoyana = TestObjectFactory.createStudentBoyana();

        Student foundStudent = TestObjectFactory.createStudentBoyana();

        assertNotNull(foundStudent);
        assertEquals(studentBoyana, foundStudent);
    }
}
