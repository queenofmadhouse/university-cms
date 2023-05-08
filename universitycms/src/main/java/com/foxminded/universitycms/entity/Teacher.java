package com.foxminded.universitycms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Builder
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers", schema = "university")
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User{

    @Column(name = "department")
    private String department;
}
