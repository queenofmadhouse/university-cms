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

    public Schedule convertFromDTOtoEntity(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .teacher(Teacher.builder().userId(scheduleDTO.getTeacher()).build())
                .course(Course.builder().courseId(scheduleDTO.getCourse()).build())
                .group(Group.builder().groupId(scheduleDTO.getGroup()).build())
                .classroomId(Classroom.builder().classroomId(scheduleDTO.getClassroom()).build())
                .lessonStart(scheduleDTO.getLessonStart())
                .lessonEnd(scheduleDTO.getLessonEnd())
                .lessonDescription(scheduleDTO.getLessonDescription())
                .build();
    }

    public ScheduleDTO convertFromEntityToDTO(Schedule schedule) {
        return ScheduleDTO.builder()
                .scheduleId(schedule.getScheduleId())
                .teacher(schedule.getScheduleId())
                .course(schedule.getCourse().getCourseId())
                .lessonName(schedule.getCourse().getCourseName())
                .group(schedule.getGroup().getGroupId())
                .classroom(schedule.getClassroomId().getClassroomId())
                .lessonStart(schedule.getLessonStart())
                .lessonEnd(schedule.getLessonEnd())
                .lessonDescription(schedule.getLessonDescription())
                .build();
    }
}
