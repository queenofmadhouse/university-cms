package com.foxminded.universitycms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true, exclude = "courses")
@Entity
@Table(name = "teachers", schema = "university")
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User{

    @Column(name = "department")
    private String department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "teachers_courses_relation",
            schema = "university",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (!Objects.equals(department, teacher.department)) return false;
        return Objects.equals(courses, teacher.courses);
    }

    @Override
    public int hashCode() {
        int result = department != null ? department.hashCode() : 0;
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }
}
