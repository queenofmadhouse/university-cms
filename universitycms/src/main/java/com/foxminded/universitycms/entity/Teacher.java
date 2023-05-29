package com.foxminded.universitycms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "teachers", schema = "university")
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User{

    @Column(name = "department")
    private String department;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teachers_courses_relation",
            schema = "university",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @ToString.Exclude
    private Set<Course> courses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getUserId(), teacher.getUserId()) &&
                Objects.equals(getFirstName(), teacher.getFirstName()) &&
                Objects.equals(getLastName(), teacher.getLastName()) &&
                Objects.equals(getEmail(), teacher.getEmail()) &&
                Objects.equals(getDepartment(), teacher.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDepartment());
    }
}
