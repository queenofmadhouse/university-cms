package com.foxminded.universitycms.repository;


import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
class GroupRepositoryTestIT {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void findAllByCourseShouldReturnEmptyListWhenInputCourseIsNull() {

        Course nullCourse = null;

        List<Group> foundedGroups = groupRepository.findAllByCourse(nullCourse);

        assertTrue(foundedGroups.isEmpty());
    }

    @Test
    void findAllByCourseShouldReturnEmptyListWhenRelationNotExists() {

        Course courseWithZeroExistRelations = Course.builder()
                .courseId(2L)
                .courseName("Biology")
                .courseDescription("Hard")
                .build();

        List<Group> foundedGroups = groupRepository.findAllByCourse(courseWithZeroExistRelations);

        assertTrue(foundedGroups.isEmpty());
    }

    @Test
    void findAllByCourseShouldReturnListOfGroupsRelatedToCourse() {

        Course courseMath = Course.builder()
                .courseId(1L)
                .courseName("Math")
                .courseDescription("Hard")
                .build();

        Group groupA5 = Group.builder()
                .groupId(1L)
                .groupName("A5").build();

        Group groupB3 = Group.builder()
                .groupId(2L)
                .groupName("B3").build();

        List<Group> foundedGroups = groupRepository.findAllByCourse(courseMath);

        assertNotNull(foundedGroups);
        assertEquals(2, foundedGroups.size());
        assertEquals(groupA5, foundedGroups.get(0));
        assertEquals(groupB3, foundedGroups.get(1));
    }
}
