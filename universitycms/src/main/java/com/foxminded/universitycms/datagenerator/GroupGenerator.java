package com.foxminded.universitycms.datagenerator;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;

import java.util.List;

public interface GroupGenerator {

    List<Group> generateData(List<Course> courses);
}
