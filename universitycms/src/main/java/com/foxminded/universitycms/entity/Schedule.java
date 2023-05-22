package com.foxminded.universitycms.entity;

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
    private LocalDateTime lessonStart;

    @Column(name = "lesson_end")
    private LocalDateTime lessonEnd;

    @Column(name = "lesson_description")
    private String lessonDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (!Objects.equals(scheduleId, schedule.scheduleId)) return false;
        if (!Objects.equals(teacher, schedule.teacher)) return false;
        if (!Objects.equals(course, schedule.course)) return false;
        if (!Objects.equals(group, schedule.group)) return false;
        if (!Objects.equals(lessonStart, schedule.lessonStart))
            return false;
        if (!Objects.equals(lessonEnd, schedule.lessonEnd)) return false;
        return Objects.equals(lessonDescription, schedule.lessonDescription);
    }

    @Override
    public int hashCode() {
        int result = scheduleId != null ? scheduleId.hashCode() : 0;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (lessonStart != null ? lessonStart.hashCode() : 0);
        result = 31 * result + (lessonEnd != null ? lessonEnd.hashCode() : 0);
        result = 31 * result + (lessonDescription != null ? lessonDescription.hashCode() : 0);
        return result;
    }
}
