package com.foxminded.universitycms.service;

import com.foxminded.universitycms.entity.Group;
import com.foxminded.universitycms.entity.Schedule;
import com.foxminded.universitycms.entity.Teacher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ScheduleService {

    Map<LocalDate, List<Schedule>> findScheduleByTeacher(Teacher teacher, int days);

    Map<LocalDate, List<Schedule>> findScheduleByGroup(Group group, int days);

    Schedule findById(long id);

    List<LocalTime> findFreeTimeForTeacherAndGroup(LocalDate date, Teacher teacher, Group group);

    void save(Schedule schedule);

    void saveAll(List<Schedule> schedules);
}
