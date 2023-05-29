package com.foxminded.universitycms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @Column(name = "lesson_start")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lessonStart;

    @Column(name = "lesson_end")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lessonEnd;

    @Column(name = "lesson_description")
    private String lessonDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(scheduleId, schedule.scheduleId) &&
                Objects.equals(teacher, schedule.teacher) &&
                Objects.equals(course, schedule.course) &&
                Objects.equals(group, schedule.group) &&
                Objects.equals(lessonStart, schedule.lessonStart) &&
                Objects.equals(lessonEnd, schedule.lessonEnd) &&
                Objects.equals(lessonDescription, schedule.lessonDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId, teacher, course, group, lessonStart, lessonEnd, lessonDescription);
    }
}
