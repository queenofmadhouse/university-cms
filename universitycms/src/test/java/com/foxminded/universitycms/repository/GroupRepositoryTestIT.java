package com.foxminded.universitycms.repository;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
class GroupRepositoryTestIT {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void findAllByCoursesShouldReturnListOfGroupsRelatedToCourse() {

        Course courseMath = Course.builder()
                .courseId(1L)
                .courseName("Math")
                .courseDescription("Hard")
                .build();

        List<Group> foundedGroups = groupRepository.findAllByCourses(courseMath);

        assertNotNull(foundedGroups);
        assertEquals(2, foundedGroups.size());
    }
}
