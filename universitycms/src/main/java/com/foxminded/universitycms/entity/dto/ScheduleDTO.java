package com.foxminded.universitycms.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDTO {

    private Long scheduleId;

    private Long teacher;

    private Long course;

    private Long group;

    private Long classroomId;

    private LocalDateTime lessonStart;

    private LocalDateTime lessonEnd;

    private String lessonDescription;
}
