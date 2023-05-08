package com.foxminded.universitycms.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teachers_courses_relation", schema = "university")
public class TeacherCourseRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
