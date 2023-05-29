package com.foxminded.universitycms.datagenerator;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Student;

import java.util.List;

public interface StudentsGenerator {

    List<Student> generateData(List<Group> groups);
}
