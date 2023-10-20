package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Classroom;
import com.foxminded.universitycms.testconfiguration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
@Transactional
class ClassroomServiceImplTestIT {

    @Autowired
    private ClassroomServiceImpl classroomService;

    @Test
    void findFreeClassroomsShouldReturnListOfFreeClassroomsForTimeSlot() {

        LocalDateTime startOfATimeSlot = LocalDate.now().atStartOfDay().plusHours(8);
        Classroom expectedClassroom = Classroom.builder()
                .classroomId(2L)
                .build();

        List<Classroom> foundFreeClassrooms = classroomService.findFreeClassrooms(startOfATimeSlot);

        assertNotNull(foundFreeClassrooms);
        assertEquals(1, foundFreeClassrooms.size());
        assertEquals(expectedClassroom.getClassroomId(), foundFreeClassrooms.get(0).getClassroomId());
    }
}
