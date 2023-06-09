package com.foxminded.universitycms.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleDTO {

    private Long scheduleId;

    private Long teacher;

    private Long course;

    private Long group;

    private Long classroom;

    private LocalDateTime lessonStart;

    private LocalDateTime lessonEnd;

    private String lessonDescription;
}
