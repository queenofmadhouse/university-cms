package com.foxminded.universitycms.datagenerator;

import com.foxminded.universitycms.entity.Classroom;
import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;

import java.util.List;

public interface ScheduleGenerator {

    List<Schedule> generateData(List<Group> groups, List<Classroom> classrooms);
}
