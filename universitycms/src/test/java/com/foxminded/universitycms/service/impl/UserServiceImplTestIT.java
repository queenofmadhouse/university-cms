package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.User;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import com.foxminded.universitycms.testojectsfactory.TestObjectFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
@Transactional
class UserServiceImplTestIT {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void loadUserByUsernameShouldReturnUserDetailsForTeacher() {

        Teacher teacherAlex = TestObjectFactory.createTeacherAlex();
        Set<GrantedAuthority> expectedRoles = new HashSet<>();
        expectedRoles.add(new SimpleGrantedAuthority("ROLE_TEACHER"));

        UserDetails foundUserDetails = userService.loadUserByUsername(teacherAlex.getEmail());

        assertNotNull(foundUserDetails);
        assertEquals(teacherAlex.getEmail(), foundUserDetails.getUsername());
        assertEquals(teacherAlex.getPassword(), foundUserDetails.getPassword());
        assertEquals(expectedRoles, foundUserDetails.getAuthorities());
    }

    @Test
    void loadUserByUsernameShouldReturnUserDetailsForStudent() {

        Student studentBoyana = TestObjectFactory.createStudentBoyana();
        Set<GrantedAuthority> expectedRoles = new HashSet<>();
        expectedRoles.add(new SimpleGrantedAuthority("ROLE_STUDENT"));

        UserDetails foundUserDetails = userService.loadUserByUsername(studentBoyana.getEmail());

        assertNotNull(foundUserDetails);
        assertEquals(studentBoyana.getEmail(), foundUserDetails.getUsername());
        assertEquals(studentBoyana.getPassword(), foundUserDetails.getPassword());
        assertEquals(expectedRoles, foundUserDetails.getAuthorities());
    }

    @Test
    void findByEmailShouldReturnEntityFoundByEmail() {

        User teacherAlex = TestObjectFactory.createTeacherAlex();

        User foundUser = userService.findByEmail(teacherAlex.getEmail());

        assertNotNull(foundUser);
        assertEquals(teacherAlex, foundUser);
    }
}
