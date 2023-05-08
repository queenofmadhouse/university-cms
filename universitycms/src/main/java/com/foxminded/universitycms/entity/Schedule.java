package com.foxminded.universitycms.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "schedule", schema = "university")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long scheduleId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "lesson_date")
    private LocalDateTime lessonDate;

    @Column(name = "lesson_duration")
    private LocalDateTime lessonDuration;

    @Column(name = "lesson_description")
    private String lessonDescription;
}
