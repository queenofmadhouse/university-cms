package com.foxminded.universitycms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "admins", schema = "university")
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {

    @Column(name = "department")
    private String department;
}
