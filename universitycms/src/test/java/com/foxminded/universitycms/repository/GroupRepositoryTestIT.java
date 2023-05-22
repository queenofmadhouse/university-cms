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

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findAllByCoursesShouldReturnListOfGroupsRelatedToCourse() {

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

        courseRepository.save(courseMath);
        groupRepository.save(groupA5);
        groupRepository.save(groupB3);

        groupRepository.addGroupToCourse(groupA5, courseMath);
        groupRepository.addGroupToCourse(groupB3, courseMath);

        List<Group> foundedGroups = groupRepository.findAllByCourses(courseMath);

        assertNotNull(foundedGroups);
        assertEquals(2, foundedGroups.size());
        assertEquals(groupA5, foundedGroups.get(0));
        assertEquals(groupB3, foundedGroups.get(1));
    }
}
