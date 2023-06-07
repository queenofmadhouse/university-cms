package com.foxminded.universitycms.converter;

import com.foxminded.universitycms.entity.Classroom;
import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.dto.ScheduleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ScheduleConverter {

    public Schedule convert(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .teacher(Teacher.builder().userId(scheduleDTO.getTeacher()).build())
                .course(Course.builder().courseId(scheduleDTO.getCourse()).build())
                .group(Group.builder().groupId(scheduleDTO.getGroup()).build())
                .classroomId(Classroom.builder().classroomId(scheduleDTO.getClassroomId()).build())
                .lessonStart(scheduleDTO.getLessonStart())
                .lessonEnd(scheduleDTO.getLessonEnd())
                .lessonDescription(scheduleDTO.getLessonDescription())
                .build();
    }
}
