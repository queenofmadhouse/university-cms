package com.foxminded.universitycms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
