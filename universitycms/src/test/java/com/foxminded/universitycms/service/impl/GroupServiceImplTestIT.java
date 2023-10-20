package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.exception.DatabaseRuntimeException;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import com.foxminded.universitycms.testojectsfactory.TestObjectFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@IntegrationTest
@Transactional
class GroupServiceImplTestIT {

    @Autowired
    private GroupServiceImpl groupService;

    @Test
    void findAllByCourseShouldReturnListOfGroupsRelatedToCourse() {

        Course courseMath = TestObjectFactory.createCourseMath();
        Group groupA5 = TestObjectFactory.createGroupA5();
        Group groupB3 = TestObjectFactory.createGroupB3();

        List<Group> foundGroups = groupService.findAllByCourse(courseMath);

        assertNotNull(foundGroups);
        assertEquals(2, foundGroups.size());
        assertEquals(groupA5, foundGroups.get(0));
        assertEquals(groupB3, foundGroups.get(1));
    }

    @Test
    void findByIdShouldReturnGroupRelatedToId() {

        Group groupA5 = TestObjectFactory.createGroupA5();

        Group foundGroup = groupService.findById(groupA5.getGroupId());

        assertNotNull(foundGroup);
        assertEquals(groupA5, foundGroup);
    }

    @Test
    void findByIdShouldThrownDatabaseRuntimeExceptionWhenCantFindGroupById() {

        Group notExistGroup = TestObjectFactory.createNotExistGroup();

        assertThrows(DatabaseRuntimeException.class,
                () -> groupService.findById(notExistGroup.getGroupId()));
    }
}
