package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Course;
import com.foxminded.universitycms.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> findAllByCourse(Course course);

    Group findById(long id);
}
